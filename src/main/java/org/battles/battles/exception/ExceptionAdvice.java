package org.battles.battles.exception;

import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.battles.battles.exception.exception.CAccessDeniedException;
import org.battles.battles.exception.exception.CAuthenticationEntryPointException;
import org.battles.battles.exception.exception.CEmailExistedException;
import org.battles.battles.exception.exception.CNicknameExistedException;
import org.battles.battles.exception.exception.CNotSchoolEmailException;
import org.battles.battles.exception.exception.CNotValidEmailException;
import org.battles.battles.exception.exception.CTokenUserNotFoundException;
import org.battles.battles.exception.exception.CUserNotFoundException;
import org.battles.battles.response.CommonResult;
import org.battles.battles.response.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Api(tags = {"시큐리티 설정 컨트롤러"})
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    // status code 제대로 설정하기 수정 필요

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult();
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult authenticationEntryPointException(HttpServletRequest request,
        Exception e) {
        return responseService.getFailResultWithMsg("해당 리소스에 접근하기 위한 권한이 없습니다.");
    }

    @ExceptionHandler(CAccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult accessDeniedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("보유한 권한으로 접근할 수 없는 리소스입니다.");
    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("해당 계정이 존재하지 않거나 잘못된 계정입니다.");
    }

    @ExceptionHandler(CTokenUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult TokenUserNotFoundException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("토큰에 해당하는 계정이 존재하지 않거나 잘못된 계정입니다.");
    }

    @ExceptionHandler(CEmailExistedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult EmailExistedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("해당 이메일로 가입할 수 없습니다. 다시 입력 시도해주세요.");
    }

    @ExceptionHandler(CNotValidEmailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult NotValidEmailException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("옳지 않은 이메일입니다. 이메일 형식을 확인해주세요");
    }

    @ExceptionHandler(CNotSchoolEmailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult NotSchoolEmailException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("잘못된 이메일입니다. 학교 계정을 다시 입력해주세요.");
    }

    @ExceptionHandler(CNicknameExistedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult NicknameExistedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("이미 존재하는 닉네임입니다. 다른 닉네임을 입력해주세요.");
    }
}
