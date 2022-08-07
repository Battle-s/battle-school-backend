package org.battles.battles.security.jwt;

import io.swagger.annotations.Api;
import org.battles.battles.exception.exception.CAuthenticationEntryPointException;
import org.battles.battles.response.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Spring Security 관련 컨트롤러"})
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public CommonResult entrypointException() {
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public CommonResult accessdeniedException() {
        throw new CAuthenticationEntryPointException();
    }

}
