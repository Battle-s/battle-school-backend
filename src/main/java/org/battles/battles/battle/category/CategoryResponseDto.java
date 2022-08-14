package org.battles.battles.battle.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.battles.battles.common.Status;
import org.battles.battles.user.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponseDto {

    private Long categoryId;

    private String categoryName;

    private Long min;

    private Long max;

    private Long timeOfBattle;

    private String basicRule;

    private User organizer;

    private Long seasonId;

    private Long createdAt;

    private Status status;
}
