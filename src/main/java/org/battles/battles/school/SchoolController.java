package org.battles.battles.school;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.battles.battles.response.CommonResult;
import org.battles.battles.response.ResponseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"2. 학교 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    private final ResponseService responseService;

    @ApiOperation(value = "모든 학교 조회", notes = "모든 학교명과 도메인 주소를 조회한다")
    @GetMapping("")
    public CommonResult getAllSchools() {
        return responseService.getListResult(schoolService.getAllSchools());
    }

}
