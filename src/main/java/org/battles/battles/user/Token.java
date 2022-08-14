package org.battles.battles.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.battles.battles.common.TimeStamped;

@Getter
@Builder
@AllArgsConstructor
public class Token extends TimeStamped {

    private final String accessToken;

    private final String refreshToken;
}
