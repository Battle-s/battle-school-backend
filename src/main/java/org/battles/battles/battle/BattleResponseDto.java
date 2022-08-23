package org.battles.battles.battle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.battles.battles.battle.category.Category;
import org.battles.battles.battle.season.Season;
import org.battles.battles.user.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BattleResponseDto {

    private Long battleId;

    private String userNickName;

    private String categoryName;

    private String categoryArea;

    private Long seasonId;

    private String seasonName;

    private Long max;

    private Long min;

    private Long duration;

    private String basicRule;

    public BattleResponseDto(Battle battle, User user, Category category, Season season) {
        this.battleId = battle.getBattleId();
        this.userNickName = user.getNickName();
        this.categoryName = category.getName();
        this.categoryArea = category.getCategoryArea().toString();
        this.seasonId = season.getSeasonId();
        this.seasonName = season.getSeasonName();
        this.max = battle.getMax();
        this.min = battle.getMin();
        this.duration = battle.getDuration();
        this.basicRule = battle.getBasicRule();
    }
}
