package org.battles.battles.exception;

import io.swagger.annotations.Api;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.battles.battles.exception.exception.*;
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

    @ExceptionHandler(CSchoolNameSchoolDomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult SchoolNameSchoolDomainException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("학교 이메일과 학교명을 확인하고 다시 입력해주세요.");
    }

    @ExceptionHandler(CExpiredTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult ExpiredTokenException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("만료된 토큰입니다. 다시 입력해주세요.");
    }

    @ExceptionHandler(CRefreshTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult RefreshTokenException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("잘못된 Refresh 토큰입니다. 다시 입력해주세요.");
    }

    @ExceptionHandler(CCategoryExistedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CategoryExistedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("이미 존재하는 경기 종목입니다.");
    }

    @ExceptionHandler(CCategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CategoryNotFoundException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("존재하지 않는 경기 종목입니다.");
    }

    @ExceptionHandler(CDateTimeValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult DateTimeValidException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("유효하지 않은 날짜 및 시간 데이터입니다. 확인한 뒤 다시 입력해주세요.");
    }

    @ExceptionHandler(CSeasonStartValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult SeasonStartValidException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("아직 이전 시즌이 진행 중입니다. 날짜 및 시간 데이터를 다시 확인해주세요. ");
    }

    @ExceptionHandler(CSeasonNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult SeasonNotFoundException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("해당 시즌이 존재하지 않습니다.");
    }

    @ExceptionHandler(CCategoryInactiveException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CategoryInactiveException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("비활성화된 경기 종목입니다. 경기 종목을 먼저 만들어주세요.");
    }

    @ExceptionHandler(CSeasonInactiveException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult SeasonInactiveException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("비활성화된 시즌입니다. 다시 확인한 뒤 입력해주세요");
    }

    @ExceptionHandler(CBattleExistedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult BattleExistedException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("이미 해당 경기 종목은 해당 시즌에 개최되어있습니다.");
    }

    @ExceptionHandler(CBattleNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult BattleNotFoundException(HttpServletRequest request, Exception e) {
        return responseService.getFailResultWithMsg("해당 경기가 존재하지 않습니다.");
    }
}
