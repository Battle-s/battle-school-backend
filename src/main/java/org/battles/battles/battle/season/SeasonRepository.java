package org.battles.battles.battle.season;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    List<Season> findAllByOrderBySeasonStartDesc();
}
