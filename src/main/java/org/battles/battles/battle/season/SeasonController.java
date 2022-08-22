package org.battles.battles.battle.season;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.battles.battles.response.CommonResult;
import org.battles.battles.response.ResponseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"4. 시즌 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class SeasonController {

    private final ResponseService responseService;

    private final SeasonService seasonService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "시즌 생성", notes = "시즌을 생성한다")
    @PostMapping("/season")
    public CommonResult create(@RequestBody SeasonRequestDto seasonRequestDto) {
        return responseService.getSingleResult(seasonService.create(seasonRequestDto));
    }

    @ApiOperation(value = "시즌 목록 조회", notes = "시즌 목록을 조회한다")
    @GetMapping("/seasons")
    public CommonResult getSeasons() {
        return responseService.getListResult(seasonService.getSeasons());
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "시즌 종료 설정", notes = "시즌을 종료한다")
    @PatchMapping("/seasons/{seasonId}")
    public CommonResult setSeasonInactive(@PathVariable Long seasonId) {
        seasonService.setSeasonInactive(seasonId);

        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "현재 시즌 조회", notes = "현재 시즌을 조회한다")
    @GetMapping("/seasons/now")
    public CommonResult getNowSeason() {
        Optional<Season> season = seasonService.getNowSeason();

        if (season.isEmpty()) {
            return responseService.getFailResultWithMsg("현재 진행 중인 시즌이 존재하지 않습니다.");
        }

        return responseService.getSingleResult(season.get());
    }
}
