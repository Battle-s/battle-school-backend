package org.battles.battles.battle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.battles.battles.battle.category.Category;
import org.battles.battles.battle.category.CategoryService;
import org.battles.battles.battle.season.Season;
import org.battles.battles.battle.season.SeasonService;
import org.battles.battles.common.Status;
import org.battles.battles.exception.exception.CAccessDeniedException;
import org.battles.battles.exception.exception.CBattleExistedException;
import org.battles.battles.exception.exception.CBattleNotFoundException;
import org.battles.battles.exception.exception.CCategoryInactiveException;
import org.battles.battles.exception.exception.CCategoryNotFoundException;
import org.battles.battles.exception.exception.CSeasonInactiveException;
import org.battles.battles.exception.exception.CSeasonNotFoundException;
import org.battles.battles.exception.exception.CUserNotFoundException;
import org.battles.battles.user.User;
import org.battles.battles.user.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BattleService {

    private final BattleRepository battleRepository;

    private final CategoryService categoryService;

    private final SeasonService seasonService;

    private final UserService userService;

    @Transactional
    public BattleResponseDto create(String email, BattleRequestDto battleRequestDto) {
        Optional<User> user = userService.getUserByEmail(email);

        if (user.isEmpty()) {
            throw new CUserNotFoundException();
        }

        Optional<Category> category = categoryService.getCategoryByName(battleRequestDto.getCategoryName());

        if (category.isEmpty()) {
            throw new CCategoryNotFoundException();
        }

        if (!category.get().getStatus().equals(Status.ACTIVE)) {
            throw new CCategoryInactiveException();
        }

        Optional<Season> season = seasonService.getSeasonById(battleRequestDto.getSeasonId());

        if (season.isEmpty()) {
            throw new CSeasonNotFoundException();
        }

        if (!season.get().getStatus().equals(Status.ACTIVE)) {
            throw new CSeasonInactiveException();
        }

        Optional<Battle> battle = battleRepository.findBattleByCategoryAndSeason(category.get(), season.get());

        if (battle.isPresent()) {
            throw new CBattleExistedException();
        }

        Battle newBattle = Battle.builder()
            .category(category.get())
            .organizer(user.get())
            .season(season.get())
            .max(battleRequestDto.getMax())
            .min(battleRequestDto.getMin())
            .duration(battleRequestDto.getDuration())
            .basicRule(battleRequestDto.getBasicRule())
            .build();

        battleRepository.save(newBattle);

        return new BattleResponseDto(newBattle, user.get(), category.get(), season.get());
    }

    @Transactional
    public void delete(String email, Long battleId) {
        User user;

        if (userService.getUserByEmail(email).isEmpty()) {
            throw new CUserNotFoundException();
        }

        Battle battle;

        if (battleRepository.findById(battleId).isEmpty()) {
            throw new CBattleExistedException();
        }

        user = userService.getUserByEmail(email).get();

        battle = battleRepository.findById(battleId).get();

        if (!battle.getOrganizer().equals(user)) {
            throw new CAccessDeniedException();
        }

        battleRepository.delete(battle);
    }

    @Transactional
    public List<BattleResponseDto> getBattles() {
        return battleRepository.findAll()
            .stream()
            .map(this::convert)
            .collect(Collectors.toList());
    }

    private BattleResponseDto convert(Battle battle) {
        Category category = battle.getCategory();

        Season season = battle.getSeason();

        return BattleResponseDto.builder()
            .battleId(battle.getBattleId())
            .userNickName(battle.getOrganizer().getNickName())
            .categoryName(category.getName())
            .categoryArea(category.getCategoryArea().toString())
            .seasonId(season.getSeasonId())
            .seasonName(season.getSeasonName())
            .max(battle.getMax())
            .min(battle.getMin())
            .duration(battle.getDuration())
            .basicRule(battle.getBasicRule())
            .build();
    }

    @Transactional
    public Optional<BattleResponseDto> getBattleById(Long battleId) {
        if (battleRepository.findById(battleId).isEmpty()) {
            return Optional.empty();
        }

        Battle battle = battleRepository.findById(battleId).get();

        return Optional.of(convert(battle));
    }

    @Transactional
    public List<Category> getCategoryBySeason(Long seasonId) {
        if (seasonService.getSeasonById(seasonId).isEmpty()) {
            throw new CSeasonNotFoundException();
        }

        Season season = seasonService.getSeasonById(seasonId).get();

        return battleRepository.findBattleBySeason(season)
            .stream()
            .map(this::getCategoryFromBattle)
            .collect(Collectors.toList());
    }

    private Category getCategoryFromBattle(Battle battle) {
        return battle.getCategory();
    }

    @Transactional
    public BattleResponseDto getBattleByCategoryAndSeason(String categoryName, Long seasonId) {
        Category category;
        Season season;

        if (categoryService.getCategoryByName(categoryName).isEmpty()) {
            throw new CCategoryNotFoundException();
        }

        if (seasonService.getSeasonById(seasonId).isEmpty()) {
            throw new CSeasonNotFoundException();
        }

        category = categoryService.getCategoryByName(categoryName).get();

        season = seasonService.getSeasonById(seasonId).get();

        Optional<Battle> battle = battleRepository.findBattleByCategoryAndSeason(category, season);

        if(battle.isEmpty()){
            throw new CBattleNotFoundException();
        }

        return convert(battle.get());
    }

    @Transactional
    public List<BattleResponseDto> getBattlesByOrganizer(String email) {
        User organizer;

        if (userService.getUserByEmail(email).isEmpty()) {
            throw new CUserNotFoundException();
        }

        organizer = userService.getUserByEmail(email).get();

        return battleRepository.findBattleByOrganizer(organizer)
            .stream()
            .map(this::convert)
            .collect(Collectors.toList());
    }
}
