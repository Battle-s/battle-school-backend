package org.battles.battles.post;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.battles.battles.post.Dto.PostsSaveRequestDto;
import org.battles.battles.response.ResponseService;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"5. 게시판 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Slf4j
public class PostController {
    //
    private final ResponseService responseService;

    private final PostService postService;

    @ApiOperation(value = "전체 게시글 작성", notes = "회원정보와 제목, 내을 입력하여 전체 게시글을 작성한다.")
    @PostMapping("/all")
    public Long saveAll(@RequestBody PostsSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @ApiOperation(value = "학교 게시글 작성", notes = "회원정보와 제목, 내을 입력하여 학교 게시글을 작성한다.")
    @PostMapping("/{schoolId}")
    public Long saveSchool(@RequestBody PostsSaveRequestDto requestDto, @PathVariable Long schoolId) {
        return postService.saveSchool(requestDto, schoolId);
}
}
