package org.battles.battles.battle;

import java.util.List;
import java.util.Optional;
import org.battles.battles.battle.category.Category;
import org.battles.battles.battle.season.Season;
import org.battles.battles.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleRepository extends JpaRepository<Battle, Long> {
    Optional<Battle> findBattleByCategoryAndSeason(Category category, Season season);
    List<Battle> findBattleBySeason(Season season);
    List<Battle> findBattleByOrganizer(User user);
}
