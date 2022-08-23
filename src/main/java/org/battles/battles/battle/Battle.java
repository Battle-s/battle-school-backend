package org.battles.battles.battle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.battles.battles.battle.category.Category;
import org.battles.battles.battle.season.Season;
import org.battles.battles.common.TimeStamped;
import org.battles.battles.user.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Battle_Table")
public class Battle extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long battleId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "organizerId")
    private User organizer;

    @ManyToOne
    @JoinColumn(name = "seasonId")
    private Season season;

    @Column(nullable = false)
    private Long min;

    @Column(nullable = false)
    private Long max;

    private Long duration;

    private String basicRule;
}
