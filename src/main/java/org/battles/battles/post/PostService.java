package org.battles.battles.post;

import lombok.RequiredArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.exception.exception.CSchoolNotFoundException;
import org.battles.battles.exception.exception.CUserNotFoundException;
import org.battles.battles.post.Dto.PostsSaveRequestDto;
import org.battles.battles.school.School;
import org.battles.battles.school.SchoolRepository;
import org.battles.battles.user.User;
import org.battles.battles.user.UserService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final SchoolRepository schoolRepository;

    private final UserService userService;

    @Transactional
    public Long save(String email, PostsSaveRequestDto requestDto) {

        Optional<User> user = userService.getUserByEmail(email);
        if(user.isEmpty()) {
            throw new CUserNotFoundException();
        }

        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .status(Status.ACTIVE)
                .postType(PostType.ENTIRE)
                .user(user.get())
                .build();

        return postRepository.save(post).getPostId();
    }

    @Transactional
    public Long saveSchool(String email, PostsSaveRequestDto requestDto, Long schoolId) {
        Optional<User> user = userService.getUserByEmail(email);
        if(user.isEmpty()) {
            throw new CUserNotFoundException();
        }

        Optional<School> school = schoolRepository.findById(schoolId);
        if(school.isEmpty()) {
            throw new CSchoolNotFoundException();
        }

        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .status(Status.ACTIVE)
                .postType(PostType.SCHOOL)
                .user(user.get())
                .school(school.get())
                .build();

        return postRepository.save(post).getPostId();
    }
}
