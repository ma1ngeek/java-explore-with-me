package ru.practicum.stats_common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Common {
    public static final String DT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern(DT_FORMAT);
    public static final String STATS_ENDPOINT = "/stats";
    public static final String HIT_ENDPOINT = "/hit";
}
