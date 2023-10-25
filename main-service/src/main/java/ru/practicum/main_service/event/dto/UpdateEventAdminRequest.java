package ru.practicum.main_service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEventAdminRequest {
    @Size(min = MainCommon.MIN_LENGTH_ANNOTATION, max = MainCommon.MAX_LENGTH_ANNOTATION)
    String annotation;

    Long category;

    @Size(min = MainCommon.MIN_LENGTH_DESCRIPTION, max = MainCommon.MAX_LENGTH_DESCRIPTION)
    String description;

    @JsonFormat(pattern = MainCommon.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    LocalDateTime eventDate;

    @Valid
    LocationDto location;

    Boolean paid;

    @PositiveOrZero
    Integer participantLimit;

    Boolean requestModeration;

    EventStateAction stateAction;

    @Size(min = MainCommon.MIN_LENGTH_TITLE, max = MainCommon.MAX_LENGTH_TITLE)
    String title;
}
