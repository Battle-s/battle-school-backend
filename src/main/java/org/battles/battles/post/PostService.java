package org.battles.battles.post;

import lombok.RequiredArgsConstructor;
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
        PostType postType;
        postType = PostType.ENTIRE;

        Optional<User> user = userService.getUserByEmail(email);
        if(user.isEmpty()) {
            throw new CUserNotFoundException();
        }
        return postRepository.save(requestDto.toEntity(postType, user.get())).getPostId();
    }

    @Transactional
    public Long saveSchool(String email, PostsSaveRequestDto requestDto, Long schoolId) {
        PostType postType;
        postType = PostType.SCHOOL;

        Optional<User> user = userService.getUserByEmail(email);
        if(user.isEmpty()) {
            throw new CUserNotFoundException();
        }

        Optional<School> school = schoolRepository.findById(schoolId);
        if(school.isEmpty()) {
            throw new CSchoolNotFoundException();
        }
        return postRepository.save(requestDto.toEntity2(postType, school.get(), user.get())).getPostId();
    }
}
