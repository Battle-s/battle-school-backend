package org.battles.battles.battle.season;

import java.time.LocalDateTime;
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
public class SeasonRequestDto {

    private String seasonName;
    private LocalDateTime seasonStart;
    private LocalDateTime seasonEnd;
}
