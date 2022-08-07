package org.battles.battles.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {

    private final String accessToken;

    private final String refreshToken;
}
