package org.battles.battles.battle.category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.exception.exception.CCategoryExistedException;
import org.battles.battles.exception.exception.CCategoryNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto) {
        if (categoryRepository.findByName(requestDto.getCategoryName()).isPresent()) {
            throw new CCategoryExistedException();
        }

        Category category = categoryRepository.save(Category.builder()
            .name(requestDto.getCategoryName())
            .categoryArea(requestDto.getCategoryArea())
            .status(Status.INACTIVE)
            .build());

        return new CategoryResponseDto(category);
    }

    @Transactional
    public List<CategoryResponseDto> findAllCategory() {
        return categoryRepository.findAll()
            .stream()
            .map(CategoryResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<CategoryResponseDto> findAllCategoryByArea(CategoryArea categoryArea) {
        return categoryRepository.findAllByCategoryArea(categoryArea)
            .stream()
            .map(CategoryResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<CategoryResponseDto> findAllCategoryStatus(Status status) {
        return categoryRepository.findAllByStatus(status)
            .stream()
            .map(CategoryResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<CategoryResponseDto> findAllCategoryByAreaAndStatus(CategoryArea categoryArea,
        Status status) {
        return categoryRepository.findAllByCategoryAreaAndStatus(categoryArea, status)
            .stream()
            .map(CategoryResponseDto::new)
            .collect(Collectors.toList());
    }


    @Transactional
    public boolean isActive(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if (category.isEmpty()) {
            throw new CCategoryNotFoundException();
        }
        return category.get().getStatus().equals(Status.ACTIVE);
    }

    @Transactional
    public void setActive(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if (category.isEmpty()) {
            throw new CCategoryNotFoundException();
        }
        category.get().setStatus(Status.ACTIVE);
        categoryRepository.save(category.get());
    }

    @Transactional
    public void setAllInActive() {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            category.setStatus(Status.INACTIVE);
            categoryRepository.save(category);
        }
    }
}
