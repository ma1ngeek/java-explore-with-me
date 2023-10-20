package ru.practicum.stats_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.stats_common.Common;
import ru.practicum.stats_common.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StatsClient extends BaseClient {
    @Autowired
    public StatsClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .build()
        );
    }

    public ResponseEntity<Object> addHit(String name, String uri, String ip, LocalDateTime timestamp) {
        log.info("Запрос на регистрацию обращения к name = {}, uri = {}, ip = {}, timestamp = {}",
                name, uri, ip, timestamp);

        EndpointHit endpointHit = EndpointHit.builder()
                .app(name)
                .uri(uri)
                .ip(ip)
                .timestamp(timestamp.format(Common.DT_FORMATTER))
                .build();
        return post(Common.HIT_ENDPOINT, endpointHit);
    }

    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end, List<String> uris) {
        return getStats(start, end, uris, null);
    }

    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end) {
        return getStats(start, end, null, null);
    }

    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end, Boolean unique) {
        return getStats(start, end, null, unique);
    }

    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        log.info("Запрос на получение статистики по параметрам start = {}, end = {}, uris = {}, unique = {}",
                start, end, uris, unique);

        if (start == null || end == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Недопустимый интервал дат.");
        }

        StringBuilder uriBuilder = new StringBuilder(Common.STATS_ENDPOINT + "?start={start}&end={end}");
        Map<String, Object> parameters = Map.of(
                "start", start.format(Common.DT_FORMATTER),
                "end", end.format(Common.DT_FORMATTER)
        );

        if (uris != null && !uris.isEmpty()) {
            for (String uri : uris) {
                uriBuilder.append("&uris=").append(uri);
            }
        }
        if (unique != null) {
            uriBuilder.append("&unique=").append(unique);
        }

        return get(uriBuilder.toString(), parameters);
    }
}
