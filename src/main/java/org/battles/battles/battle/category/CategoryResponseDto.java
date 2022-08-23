package org.battles.battles.battle.category;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.battles.battles.common.Status;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponseDto {

    private Long categoryId;

    private String categoryName;

    private CategoryArea categoryArea;

    private LocalDateTime createdAt;

    private Status status;

    public CategoryResponseDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getName();
        this.categoryArea = category.getCategoryArea();
        this.createdAt = category.getCreatedAt();
        this.status = category.getStatus();
    }
}
