package org.battles.battles.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import java.util.Date;
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

    private static final long ACCESS_TOKEN_VALID_MILISECOND = 1000L * 60 * 60 * 3;

    private static final long REFRESH_TOKEN_VALID_MILISECOND = 1000L * 60 * 60 * 24 * 14;

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
}
