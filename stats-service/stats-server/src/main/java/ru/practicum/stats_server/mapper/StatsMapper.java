package ru.practicum.stats_server.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.stats_common.model.EndpointHit;
import ru.practicum.stats_server.model.Stats;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StatsMapper {
    public static Stats toStats(EndpointHit endpointHit, LocalDateTime timestamp) {
        if (endpointHit == null) {
            return null;
        }
        Stats res = Stats.builder()
                .ip(endpointHit.getIp())
                .uri(endpointHit.getUri())
                .app(endpointHit.getApp())
                .timestamp(timestamp)
                .build();
        return res;
    }
}
