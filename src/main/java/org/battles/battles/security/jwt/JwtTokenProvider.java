package org.battles.battles.security.jwt;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.battles.battles.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.battles.battles.user.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

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
    public Token createToken(String userEmail) {
        Date now = new Date();

        String newAccessToken = JWT.create()
            .withIssuer(TOKEN_ISSUER)
            .withIssuedAt(now)
            .withExpiresAt(new Date(now.getTime() + ACCESS_TOKEN_VALID_MILISECOND))
            .withSubject(ACCESS_TOKEN_SUBJECT)
            .withClaim(TOKEN_CLAIM, userEmail)
            .sign(algorithm);

        String newRefreshToken = JWT.create()
            .withIssuer(TOKEN_ISSUER)
            .withIssuedAt(now)
            .withExpiresAt(new Date(now.getTime() + REFRESH_TOKEN_VALID_MILISECOND))
            .withSubject(REFRESH_TOKEN_SUBJECT)
            .withClaim(TOKEN_CLAIM, userEmail)
            .sign(algorithm);

        return Token.builder()
            .accessToken(newAccessToken)
            .refreshToken(newRefreshToken)
            .build();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("AUTH-TOKEN");
    }

    public Optional<DecodedJWT> getDecodedToken(String token) {
        try {
            return Optional.of(jwtVerifier.verify(token));
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }

    public String getUserEmailFromToken(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(TOKEN_CLAIM).asString();
    }
}
