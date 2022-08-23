package org.battles.battles.post.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.post.Post;
import org.battles.battles.post.PostType;
import org.battles.battles.school.School;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;



    @Builder
    public PostsSaveRequestDto(String title, String content, PostType postType, Status status) {
        this.title = title;
        this.content = content;

    }

    public Post toEntity(PostType postType) {
        return Post.builder()
                .title(title)
                .content(content)
                .status(Status.ACTIVE)
                .postType(postType)
                .build();
    }

    public Post toEntity2(PostType postType, School school) {
        return Post.builder()
                .title(title)
                .content(content)
                .status(Status.ACTIVE)
                .postType(postType)
                .school(school)
                .build();
    }
}
