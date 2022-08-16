package org.battles.battles.battle.category;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.exception.exception.CCategoryExistedException;
import org.battles.battles.exception.exception.CCategoryNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(CategoryRequestDto requestDto) {
        if (categoryRepository.findByName(requestDto.getCategoryName()).isPresent()) {
            throw new CCategoryExistedException();
        }

        return categoryRepository.save(Category.builder()
            .name(requestDto.getCategoryName())
            .categoryArea(requestDto.getCategoryArea())
            .status(Status.INACTIVE)
            .build());
    }

    @Transactional
    public boolean isActive(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if (category.isEmpty()) {
            throw new CCategoryNotFoundException();
        }
        return category.get().getStatus().equals(Status.ACTIVE);
    }
}
