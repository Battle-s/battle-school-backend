package org.battles.battles.post.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.battles.battles.battle.Battle;
import org.battles.battles.battle.category.Category;
import org.battles.battles.common.Status;
import org.battles.battles.post.Post;
import org.battles.battles.post.PostType;
import org.battles.battles.school.School;
import org.battles.battles.user.User;

import java.time.LocalDateTime;
import java.util.Optional;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsResponseDto {
    private Long postId;

    private Long userId;

    private String nickName;

    private Long schoolId;

    private String schoolName;

    private String title;

    private String content;

    private PostType postType;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public PostsResponseDto(Post post, User user) {
        this.postId = post.getPostId();
        this.userId = user.getUserId();
        this.nickName = user.getNickName();
        this.schoolId = user.getSchool().getSchoolId();
        this.schoolName = user.getSchool().getSchoolName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postType = post.getPostType();
        this.status = post.getStatus();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

}
