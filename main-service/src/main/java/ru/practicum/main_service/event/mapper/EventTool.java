package ru.practicum.main_service.event.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.service.StatsService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventTool {

    private final StatsService statsService;
    private final EventMapper eventMapper;

    public List<EventShortDto> toEventsShortDto(List<Event> events) {
        Map<Long, Long> views = statsService.getViews(events);
        Map<Long, Long> confirmedRequests = statsService.getConfirmedRequests(events);

        return events.stream()
                .map((event) -> eventMapper.toEventShortDto(
                        event,
                        confirmedRequests.getOrDefault(event.getId(), 0L),
                        views.getOrDefault(event.getId(), 0L)))
                .collect(Collectors.toList());
    }

}
