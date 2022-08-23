package org.battles.battles.battle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BattleRequestDto {

    private String categoryName;

    private Long seasonId;

    private Long max;

    private Long min;

    private Long duration;

    private String basicRule;
}
