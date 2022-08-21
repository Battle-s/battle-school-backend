package org.battles.battles.battle.category;

import java.util.List;
import java.util.Optional;
import org.battles.battles.common.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findAllByCategoryArea(CategoryArea categoryArea);

    List<Category> findAllByStatus(Status status);

    List<Category> findAllByCategoryAreaAndStatus(CategoryArea categoryArea, Status status);
}
