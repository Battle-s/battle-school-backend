package org.battles.battles.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.battles.battles.response.CommonResult;
import org.battles.battles.response.ResponseService;
import org.battles.battles.security.jwt.JwtTokenProvider;
import org.battles.battles.user.Dto.SignUpDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. 사용자 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final ResponseService responseService;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "소셜 로그인한 이메일과 회원 정보를 입력하여 계정을 생성한다.")
    @PostMapping("/signup")
    public CommonResult SignUp(@RequestBody SignUpDto signUpDto) {
        userService.signUp(signUpDto);
        Token token = jwtTokenProvider.createToken(signUpDto.getEmail());
        return responseService.getSingleResult(token);
    }

    @ApiOperation(value = "로그인", notes = "소셜 로그인한 이메일로 로그인을 한다.")
    @PostMapping("/signin")
    public CommonResult SignIn(@RequestBody String email) {
        userService.signin(email);
        Token token = jwtTokenProvider.createToken(email);
        return responseService.getSingleResult(token);
    }

    @ApiOperation(value = "모든 사용자 조회", notes = "모든 사용자 조회한다.")
    @GetMapping("/all")
    public CommonResult getAllUsers() {
        return responseService.getListResult(userService.getAllUsers());
    }

    @ApiOperation(value = "닉네임 중복 조회", notes = "닉네임 중복 여부를 조회한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK !!"),
        @ApiResponse(code = 400, message = "BAD REQUEST !!"),
        @ApiResponse(code = 404, message = "NOT FOUND !!"),
        @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/signup/{nickName}")
    public CommonResult isExistedNickName(@PathVariable String nickName) {
        if (userService.isExistedNickName(nickName)) {
            return responseService.getSuccessResult();
        } else {
            return responseService.getFailResult();
        }
    }
}
