package org.battles.battles.battle;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.battles.battles.response.CommonResult;
import org.battles.battles.response.ResponseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"5. 경기 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class BattleController {

    private final ResponseService responseService;
    private final BattleService battleService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "경기 생성", notes = "경기를 생성한다")
    @PostMapping("/battle")
    public CommonResult create(@RequestBody BattleRequestDto battleRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return responseService.getSingleResult(battleService.create(email, battleRequestDto));
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "경기 삭제", notes = "생성했던 경기를 취소한다")
    @DeleteMapping("/battle/{battleId}")
    public CommonResult delete(@PathVariable Long battleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        battleService.delete(email, battleId);

        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "모든 경기 조회", notes = "모든 경기를 조회한다")
    @GetMapping("/battles")
    public CommonResult getAllBattles() {
        return responseService.getListResult(battleService.getBattles());
    }

    @ApiOperation(value = "경기 단건 조회", notes = "경기 단건을 조회한다")
    @GetMapping("/battle/{battleId}")
    public CommonResult getBattleById(@PathVariable Long battleId) {
        return responseService.getSingleResult(battleService.getBattleById(battleId));
    }

    @ApiOperation(value = "특정 시즌의 종목별 경기 조회", notes = "특정 시즌의 종목별 경기를 조회한다")
    @GetMapping("/battle/{seasonId}/{categoryName}")
    public CommonResult getBattleByCategoryAndSeason(@PathVariable Long seasonId,
        @PathVariable String categoryName) {
        return responseService.getSingleResult(
            battleService.getBattleByCategoryAndSeason(categoryName, seasonId));
    }
}
