package org.battles.battles.user;

import static java.util.Objects.nonNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.common.ValidStringUtils;
import org.battles.battles.exception.exception.*;
import org.battles.battles.school.School;
import org.battles.battles.school.SchoolService;
import org.battles.battles.user.Dto.SignUpDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    private final SchoolService schoolService;

    private final UserService userService;

    private static final String secretKey = "secret"; // 임시

    private static final String ACCESS_TOKEN_SUBJECT = "battles-access-token";

    private static final String REFRESH_TOKEN_SUBJECT = "battles-refresh-token";

    private static final String TOKEN_CLAIM = "email";

    private static final String TOKEN_ISSUER = "battles";

    private final Algorithm algorithm = Algorithm.HMAC512(secretKey);

    //    private static final long ACCESS_TOKEN_VALID_MILISECOND = 1000L * 60 * 60 * 3; 3시간
    private static final long ACCESS_TOKEN_VALID_MILISECOND = 1000L * 60 * 60 * 24 * 10; // 10일

    //    private static final long REFRESH_TOKEN_VALID_MILISECOND = 1000L * 60 * 60 * 24 * 14;
    private static final long REFRESH_TOKEN_VALID_MILISECOND = 1000L * 60 * 60 * 24 * 14; // 14일

    private final JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(TOKEN_ISSUER).build();

    @Transactional
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(CUserNotFoundException::new);
    }

    @Transactional
    public User signin(String email) {
        return userRepository.findByEmail(email).orElseThrow(CUserNotFoundException::new);
    }

    @Transactional
    public User signUp(SignUpDto signUpDto) {
        if (userRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new CEmailExistedException();
        }
        if (!checkSchoolEmail(signUpDto.getEmail()) && nonNull(
            ValidStringUtils.getValidEmail(signUpDto.getEmail()))) {
            throw new CNotSchoolEmailException();
        }
        if (userRepository.findUserByNickName(signUpDto.getNickName()).isPresent()) {
            throw new CNicknameExistedException();
        }

        School school = schoolService.getSchoolId(signUpDto.getSchoolName(),
            getSchoolDomain(signUpDto.getEmail()));

        User newUser = User.builder()
            .email(ValidStringUtils.getValidEmail(signUpDto.getEmail()))
            .name(signUpDto.getName())
            .nickName(signUpDto.getNickName())
            .school(school)
            .phoneNumber(signUpDto.getPhoneNumber())
            .role(Role.USER)
            .status(Status.ACTIVE)
            .build();

        return userRepository.save(newUser);
    }

    @Transactional
    public boolean isExistedNickName(String nickName) {
        return userRepository.findUserByNickName(nickName).isEmpty();
    }

    private boolean checkSchoolEmail(String email) {
        return email.contains("ac.kr") || email.contains("edu");
    }

    private String getSchoolDomain(String email) {
        int idx = email.indexOf("@");
        return email.substring(idx + 1);
    }

    @Transactional
    public Token createToken(String userEmail) {
        Date now = new Date();

        String newAccessToken = createAccessToken(userEmail, now);

        String newRefreshToken = createRefreshToken(userEmail, now);

        return Token.builder()
            .accessToken(newAccessToken)
            .refreshToken(newRefreshToken)
            .build();
    }

    @Transactional
    public String createAccessToken(String userEmail, Date now) {
        return JWT.create()
            .withIssuer(TOKEN_ISSUER)
            .withIssuedAt(now)
            .withExpiresAt(new Date(now.getTime() + ACCESS_TOKEN_VALID_MILISECOND))
            .withSubject(ACCESS_TOKEN_SUBJECT)
            .withClaim(TOKEN_CLAIM, userEmail)
            .sign(algorithm);
    }

    @Transactional
    public String createRefreshToken(String userEmail, Date now) {
        return JWT.create()
            .withIssuer(TOKEN_ISSUER)
            .withIssuedAt(now)
            .withExpiresAt(new Date(now.getTime() + REFRESH_TOKEN_VALID_MILISECOND))
            .withSubject(REFRESH_TOKEN_SUBJECT)
            .withClaim(TOKEN_CLAIM, userEmail)
            .sign(algorithm);
    }

    @Transactional
    public Token refreshTokenAccessToken(String userEmail, String refreshToken) {
        if (getDecodedToken(refreshToken).isPresent()) {
            Date now = new Date();
            DecodedJWT decodedJWT = getDecodedToken(refreshToken).get();
            if (!checkRefreshTokenSubject(decodedJWT)) {
                throw new CRefreshTokenException();
            }
            if (decodedJWT.getExpiresAt().after(now)) {
                String newAccessToken = createAccessToken(userEmail, now);
                String newRefreshToken = refreshToken;
                if (decodedJWT.getExpiresAt()
                    .before(new Date(now.getTime() + (1000 * 60 * 60 * 24 * 30)))) {
                    newRefreshToken = createRefreshToken(userEmail, now);
                }
                return Token.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build();
            } else {
                throw new CExpiredTokenException();
            }
        } else {
            throw new CExpiredTokenException();
        }
    }

    private Optional<DecodedJWT> getDecodedToken(String token) {
        try {
            return Optional.of(jwtVerifier.verify(token));
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }

    @Transactional
    public Authentication getAuthentication(String token) {
        User user = loadUserByToken(token);
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    @Transactional
    public boolean validateToken(String token) {
        Date now = new Date();
        if (getDecodedToken(token).get().getExpiresAt().before(now)) {
            throw new CExpiredTokenException();
        }
        return nonNull(loadUserByToken(token));
    }

    private boolean checkRefreshTokenSubject(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject().equals(REFRESH_TOKEN_SUBJECT);
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("AUTH-TOKEN");
    }


    private String getUserEmailFromToken(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(TOKEN_CLAIM).asString();
    }

    @Transactional
    public User loadUserByToken(String token) {
        return getDecodedToken(token)
            .map(this::getUserEmailFromToken)
            .flatMap(userService::getUserByEmail)
            .orElseThrow(CTokenUserNotFoundException::new);
    }
}
