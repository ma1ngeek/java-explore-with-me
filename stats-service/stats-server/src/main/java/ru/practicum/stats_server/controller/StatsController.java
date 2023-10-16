package ru.practicum.stats_server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stats_common.Common;
import ru.practicum.stats_common.model.EndpointHit;
import ru.practicum.stats_common.model.ViewStats;
import ru.practicum.stats_server.service.StatsService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {
    private final StatsService statsService;

    @PostMapping(Common.ENDPOINT_HIT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addHit(@Valid @RequestBody EndpointHit endpointHit) {
        log.info("POST {} {}", Common.ENDPOINT_HIT, endpointHit);
        statsService.addHit(endpointHit);
    }

    @GetMapping(Common.ENDPOINT_STATS)
    public List<ViewStats> getStats(@RequestParam @DateTimeFormat(pattern = Common.DT_FORMAT) LocalDateTime start,
                                    @RequestParam @DateTimeFormat(pattern = Common.DT_FORMAT) LocalDateTime end,
                                    @RequestParam(required = false) List<String> uris,
                                    @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("GET {} start={}, end={}, uris={}, unique={}", Common.ENDPOINT_STATS, start, end, uris, unique);
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Недопустимый интервал дат.");
        }
        return statsService.getStats(start, end, uris, unique);
    }
}
