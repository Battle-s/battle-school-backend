package org.battles.battles.battle.season;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.exception.exception.CDateTimeValidException;
import org.battles.battles.exception.exception.CSeasonNotFoundException;
import org.battles.battles.exception.exception.CSeasonStartValidException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeasonService {

    private final SeasonRepository seasonRepository;

    @Transactional
    public Season create(SeasonRequestDto seasonRequestDto) {
        LocalDateTime start = seasonRequestDto.getSeasonStart();

        LocalDateTime end = seasonRequestDto.getSeasonEnd();

        if (end.isBefore(start)) {
            throw new CDateTimeValidException();
        }

        if (end.isBefore(LocalDateTime.now())) {
            throw new CDateTimeValidException();
        }

        Season preSeason = getLastSeason();

        if (start.isBefore(preSeason.getSeasonEnd()) && preSeason.getStatus().equals(Status.ACTIVE)) {
            throw new CSeasonStartValidException();
        }

        return seasonRepository.save(Season.builder()
            .seasonName(seasonRequestDto.getSeasonName())
            .seasonStart(seasonRequestDto.getSeasonStart())
            .seasonEnd(seasonRequestDto.getSeasonEnd())
            .status(Status.ACTIVE)
            .build());
    }

    private Season getLastSeason() {
        return seasonRepository.findAllByOrderBySeasonStartDesc().get(0);
    }

    @Transactional
    public List<Season> getSeasons() {
        return seasonRepository.findAllByOrderBySeasonStartDesc();
    }

    @Transactional
    public void setSeasonInactive(Long seasonId) {
        Optional<Season> season = seasonRepository.findById(seasonId);

        if (season.isEmpty()) {
            throw new CSeasonNotFoundException();
        }

        Season updated = season.get();

        updated.setStatus(Status.INACTIVE);

        seasonRepository.save(updated);
    }

    @Transactional
    public Optional<Season> getNowSeason() {
        LocalDateTime now = LocalDateTime.now();

        List<Season> seasons = seasonRepository.findAllByOrderBySeasonStartDesc();

        Optional<Season> nowSeason = Optional.empty();

        for (Season season : seasons) {
            if (season.getSeasonStart().isBefore(now) && season.getSeasonEnd().isAfter(now)) {
                nowSeason = Optional.of(season);
                break;
            }

            if (season.getSeasonStart().isBefore(now) && season.getSeasonEnd().isBefore(now)) {
                break;
            }
        }
        return nowSeason;
    }

    @Transactional
    public Optional<Season> getSeasonById(Long seasonId) {
        return seasonRepository.findById(seasonId);
    }
}
