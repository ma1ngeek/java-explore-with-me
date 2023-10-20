package ru.practicum.main_service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.main_service.MainCommon;
import ru.practicum.main_service.event.enums.EventStateAction;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEventUserRequest {
    @Size(min = MainCommon.MIN_LENGTH_ANNOTATION, max = MainCommon.MAX_LENGTH_ANNOTATION)
    private String annotation;

    private Long category;

    @Size(min = MainCommon.MIN_LENGTH_DESCRIPTION, max = MainCommon.MAX_LENGTH_DESCRIPTION)
    private String description;

    @JsonFormat(pattern = MainCommon.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime eventDate;

    @Valid
    private LocationDto location;

    private Boolean paid;

    @PositiveOrZero
    private Integer participantLimit;

    private Boolean requestModeration;

    private EventStateAction stateAction;

    @Size(min = MainCommon.MIN_LENGTH_TITLE, max = MainCommon.MAX_LENGTH_TITLE)
    private String title;
}
