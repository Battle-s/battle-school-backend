package org.battles.battles.post;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.battles.battles.post.Dto.PostsSaveRequestDto;
import org.battles.battles.response.CommonResult;
import org.battles.battles.response.ResponseService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@Api(tags = {"6. 게시판 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Slf4j
public class PostController {
    //
    private final ResponseService responseService;

    private final PostService postService;
    @ApiImplicitParams({
            @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "전체 게시글 작성", notes = "회원정보와 제목, 내용을 입력하여 전체 게시글을 작성한다.")
    @PostMapping("/all")
    public CommonResult saveEntire(@RequestBody PostsSaveRequestDto requestDto) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return responseService.getSingleResult(postService.saveEntire(email, requestDto));
       // return postService.save(email, requestDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "학교 게시글 작성", notes = "회원정보와 제목, 내용을 입력하여 학교 게시글을 작성한다.")
    @PostMapping("/{schoolId}")
    public CommonResult saveSchool(@RequestBody PostsSaveRequestDto requestDto, @PathVariable Long schoolId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return responseService.getSingleResult(postService.saveSchool(email, requestDto, schoolId));
}
    @ApiOperation(value = "게시글 단건 조회", notes = "게시글 단건을 조회한다.")
    @GetMapping("/{postId}")
    public CommonResult getPostById(@PathVariable Long postId) {


        return responseService.getSingleResult(postService.getPostById(postId));
    }

    @ApiOperation(value = "모든 게시글 조회", notes = "모든 게시글을 조회한다.")
    @GetMapping("/")
    public void getAllPosts() {
    }

    @ApiOperation(value = "전체 게시글 조회", notes = "전체 게시글을 조회한다.")
    @GetMapping("/all")
    public void getEntirePosts() {

    }

    @ApiOperation(value = "학교별 게시글 조회", notes = "학교 게시글을 조회한다.")
    @GetMapping("/{schoolId}")
    public void getSchoolPosts(@PathVariable Long schoolId) {

    }


}
