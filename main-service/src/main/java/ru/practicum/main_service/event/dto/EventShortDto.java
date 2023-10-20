package ru.practicum.main_service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.main_service.MainCommon;
import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventShortDto {
    private String annotation;
    private CategoryDto category;
    private Long confirmedRequests;

    @JsonFormat(pattern = MainCommon.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime eventDate;

    private Long id;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Long views;
}
