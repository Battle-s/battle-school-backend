package org.battles.battles.post.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.post.Post;
import org.battles.battles.post.PostType;

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

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .status(Status.ACTIVE)
                .postType(PostType.ENTIRE)
                .build();
    }
}
