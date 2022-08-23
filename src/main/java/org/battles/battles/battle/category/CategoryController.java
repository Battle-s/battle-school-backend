package org.battles.battles.battle.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.battles.battles.battle.BattleService;
import org.battles.battles.common.Status;
import org.battles.battles.response.CommonResult;
import org.battles.battles.response.ResponseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"3. 종목 컨트롤러"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CategoryController {

    private final ResponseService responseService;

    private final CategoryService categoryService;

    private final BattleService battleService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "종목 생성", notes = "경기 종목을 생성한다")
    @PostMapping("/category")
    public CommonResult create(@RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto category = categoryService.createCategory(categoryRequestDto);

        return responseService.getSingleResult(category);
    }

    @ApiOperation(value = "종목 목록 조회", notes = "모든 경기 종목을 조회한다")
    @GetMapping("/categories")
    public CommonResult getCategories() {
        return responseService.getListResult(categoryService.findAllCategory());
    }

    @ApiOperation(value = "분야별 종목 목록 조회", notes = "분야별 모든 경기 종목을 조회한다. SPORT, ESPORT, BOARDGAME, STUDY, ETC")
    @GetMapping("/categories/{categoryArea}")
    public CommonResult getCategoriesByArea(@PathVariable CategoryArea categoryArea) {
        return responseService.getListResult(categoryService.findAllCategoryByArea(categoryArea));
    }

    @ApiOperation(value = "진행 중인 종목 목록 조회", notes = "진행 중인 모든 경기 종목을 조회한다.")
    @GetMapping("/categories/active")
    public CommonResult getActiveCategories() {
        return responseService.getListResult(categoryService.findAllCategoryStatus(Status.ACTIVE));
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "진행 중인 분야별 종목 목록 조회", notes = "분야별 진행 중인 모든 경기 종목을 조회한다. SPORT, ESPORT, BOARDGAME, STUDY, ETC")
    @GetMapping("/categories/{categoryArea}/active")
    public CommonResult getActiveCategoriesByArea(@PathVariable CategoryArea categoryArea) {
        return responseService.getListResult(
            categoryService.findAllCategoryByAreaAndStatus(categoryArea, Status.ACTIVE));
    }

    @ApiOperation(value = "종목 상태 조회", notes = "경기 종목이 진행 중인지 조회한다")
    @GetMapping("/categories/{categoryName}/isActive")
    public CommonResult isCategoryActive(@PathVariable String categoryName) {
        if (categoryService.isActive(categoryName)) {
            return responseService.getSuccessResult();
        }

        return responseService.getFailResultWithMsg("진행 중이지 않습니다.");
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "경기 종목 활성화", notes = "경기 종목을 진행중으로 활성화한다")
    @PatchMapping("/categories/{categoryName}/active")
    public CommonResult setCategoryActive(@PathVariable String categoryName) {
        categoryService.setActive(categoryName);

        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "모든 경기 종목 비활성화", notes = "모든 경기 종목을 비활성화한다")
    @PatchMapping("/categories/inactive")
    public CommonResult setCategoriesInActive() {
        categoryService.setAllInActive();

        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "시즌별 경기 종목 조회", notes = "시즌별 경기 종목 목록을 조회한다")
    @GetMapping("/categories/{seasonId}")
    public CommonResult getCategoriesBySeason(@PathVariable Long seasonId) {
        return responseService.getListResult(battleService.getCategoryBySeason(seasonId));
    }
}
