package org.battles.battles.user;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.battles.battles.response.CommonResult;
import org.battles.battles.response.ResponseService;
import org.battles.battles.user.Dto.SignUpDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. 사용자 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final ResponseService responseService;

    private final UserService userService;

    private final AuthService authService;

    @ApiOperation(value = "회원가입", notes = "소셜 로그인한 이메일과 회원 정보를 입력하여 계정을 생성한다.")
    @PostMapping("/signup")
    public CommonResult SignUp(@RequestBody SignUpDto signUpDto) {
        authService.signUp(signUpDto);
        Token token = authService.createToken(signUpDto.getEmail());
        return responseService.getSingleResult(token);
    }

    @ApiOperation(value = "로그인", notes = "소셜 로그인한 이메일로 로그인을 한다.")
    @PostMapping("/signin")
    public CommonResult SignIn(@RequestBody String email) {
        authService.signin(email);
        Token token = authService.createToken(email);
        return responseService.getSingleResult(token);
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "모든 사용자 조회", notes = "모든 사용자 조회한다.")
    @GetMapping("/all")
    public CommonResult getAllUsers() {
        return responseService.getListResult(userService.getAllUsers());
    }

    @ApiOperation(value = "닉네임 중복 조회", notes = "닉네임 중복 여부를 조회한다.")
    @GetMapping("/signup/{nickName}")
    public CommonResult isExistedNickName(@PathVariable String nickName) {
        if (authService.isExistedNickName(nickName)) {
            return responseService.getSuccessResult();
        } else {
            return responseService.getFailResult();
        }
    }

    @ApiOperation(value = "AccessToken 재발급", notes = "RefreshToken을 헤더에 넣어 AccessToken을 재발급 받는다")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 refresh_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/token")
    public CommonResult getAccessTokenFromRefreshToken(
        @RequestHeader(value = "AUTH-TOKEN") String refreshToken) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return responseService.getSingleResult(
            authService.refreshTokenAccessToken(email, refreshToken));
    }
}
