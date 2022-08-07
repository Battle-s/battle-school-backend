package org.battles.battles.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import lombok.RequiredArgsConstructor;
import org.battles.battles.exception.exception.CTokenUserNotFoundException;
import org.battles.battles.exception.exception.CUserNotFoundException;
import org.battles.battles.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(CUserNotFoundException::new);
    }

    @Transactional
    public User loadUserByToken(String token) {
        return jwtTokenProvider.getDecodedToken(token)
            .map(jwtTokenProvider::getUserEmailFromToken)
            .flatMap(userRepository::findByEmail)
            .orElseThrow(CTokenUserNotFoundException::new);
    }

    @Transactional
    public Authentication getAuthentication(String token) {
        User user = loadUserByToken(token);
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    // 토큰 유효 체크 로직 추가 예정
}
