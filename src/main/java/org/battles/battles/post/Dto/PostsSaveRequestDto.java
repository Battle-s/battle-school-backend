package org.battles.battles.post.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.post.Post;
import org.battles.battles.post.PostType;
import org.battles.battles.school.School;
import org.battles.battles.user.User;

import java.util.Optional;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;


}
