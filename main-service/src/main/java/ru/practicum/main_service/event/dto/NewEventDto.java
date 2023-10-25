package ru.practicum.main_service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.main_service.MainCommon;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewEventDto {
    @NotBlank
    @Size(min = MainCommon.MIN_LENGTH_ANNOTATION, max = MainCommon.MAX_LENGTH_ANNOTATION)
    String annotation;

    @NotNull
    Long category;

    @NotBlank
    @Size(min = MainCommon.MIN_LENGTH_DESCRIPTION, max = MainCommon.MAX_LENGTH_DESCRIPTION)
    String description;

    @NotNull
    @JsonFormat(pattern = MainCommon.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    LocalDateTime eventDate;

    @NotNull
    @Valid
    LocationDto location;

    @Builder.Default
    Boolean paid = false;

    @PositiveOrZero
    @Builder.Default
    Integer participantLimit = 0;

    @Builder.Default
    Boolean requestModeration = true;

    @NotBlank
    @Size(min = MainCommon.MIN_LENGTH_TITLE, max = MainCommon.MAX_LENGTH_TITLE)
    String title;
}
