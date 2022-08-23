package org.battles.battles.post;

import lombok.RequiredArgsConstructor;
import org.battles.battles.exception.exception.CNotSchoolEmailException;
import org.battles.battles.exception.exception.CSchoolNotFoundException;
import org.battles.battles.exception.exception.CUserNotFoundException;
import org.battles.battles.post.Dto.PostsSaveRequestDto;
import org.battles.battles.school.School;
import org.battles.battles.school.SchoolRepository;
import org.battles.battles.school.SchoolService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final SchoolRepository schoolRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        PostType postType;
        postType = PostType.ENTIRE;

        return postRepository.save(requestDto.toEntity(postType)).getPostId();
    }

    @Transactional
    public Long saveSchool(PostsSaveRequestDto requestDto, Long schoolId) {
        PostType postType;
        postType = PostType.SCHOOL;

        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isEmpty()) {
            throw new CSchoolNotFoundException();
        }
        return postRepository.save(requestDto.toEntity2(postType, school.get())).getPostId();
    }
}
