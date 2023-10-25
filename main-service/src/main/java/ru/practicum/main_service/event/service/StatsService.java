package ru.practicum.main_service.event.service;

import ru.practicum.main_service.event.model.Event;
import ru.practicum.stats_common.model.ViewStats;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface StatsService {
    void addHit(HttpServletRequest request);

    List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);

    Map<Long, Long> getViews(Collection<Event> events);

    Map<Long, Long> getConfirmedRequests(Collection<Event> events);
}
