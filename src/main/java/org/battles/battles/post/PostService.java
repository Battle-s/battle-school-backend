package org.battles.battles.post;

import lombok.RequiredArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.exception.exception.CPostNotFoundException;
import org.battles.battles.exception.exception.CSchoolNotFoundException;
import org.battles.battles.exception.exception.CUserNotFoundException;
import org.battles.battles.post.Dto.PostsResponseDto;
import org.battles.battles.post.Dto.PostsSaveRequestDto;
import org.battles.battles.school.School;
import org.battles.battles.school.SchoolRepository;
import org.battles.battles.user.User;
import org.battles.battles.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final UserService userService;

    @Transactional
    public PostsResponseDto saveEntire(String email, PostsSaveRequestDto requestDto) {

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
                .school(user.get().getSchool())
                .build();

        postRepository.save(post);

        return new PostsResponseDto(post);
    }

    @Transactional
    public PostsResponseDto saveSchool(String email, PostsSaveRequestDto requestDto, Long schoolId) {
        Optional<User> user = userService.getUserByEmail(email);

        if(user.isEmpty()) {
            throw new CUserNotFoundException();
        }

        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .status(Status.ACTIVE)
                .postType(PostType.SCHOOL)
                .user(user.get())
                .school(user.get().getSchool())
                .build();

        postRepository.save(post);

        return new PostsResponseDto(post);
    }

    @Transactional
    public PostsResponseDto getPostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if(post.isEmpty()) {
            throw new CPostNotFoundException();
        }

        System.out.println(new PostsResponseDto(post.get()));
        return new PostsResponseDto(post.get());
    }

    @Transactional
    public List<PostsResponseDto> getPostAllEntire() {

        List<Post> posts = postRepository.findAllByPostType(PostType.ENTIRE);

        List<PostsResponseDto> postsResponseDtos = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            postsResponseDtos.add(new PostsResponseDto(posts.get(i)));
        }
        return postsResponseDtos;
    }
    @Transactional
    public List<PostsResponseDto> getPostAllSchool(Long schoolId) {
        List<Post> posts = postRepository.findAllByPostType(PostType.SCHOOL);

        List<PostsResponseDto> postsResponseDtos = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            postsResponseDtos.add(new PostsResponseDto(posts.get(i)));
        }
        return postsResponseDtos;
    }

    @Transactional
    public List<PostsResponseDto> getPostAll() {
        List<Post> posts = postRepository.findAll();

        List<PostsResponseDto> postsResponseDtos = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            postsResponseDtos.add(new PostsResponseDto(posts.get(i)));
        }

        return postsResponseDtos;
    }
}
