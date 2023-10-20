package ru.practicum.main_service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class NewEventDto {
    @NotBlank
    @Size(min = MainCommon.MIN_LENGTH_ANNOTATION, max = MainCommon.MAX_LENGTH_ANNOTATION)
    private String annotation;

    @NotNull
    private Long category;

    @NotBlank
    @Size(min = MainCommon.MIN_LENGTH_DESCRIPTION, max = MainCommon.MAX_LENGTH_DESCRIPTION)
    private String description;

    @NotNull
    @JsonFormat(pattern = MainCommon.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime eventDate;

    @NotNull
    @Valid
    private LocationDto location;

    @Builder.Default
    private Boolean paid = false;

    @PositiveOrZero
    @Builder.Default
    private Integer participantLimit = 0;

    @Builder.Default
    private Boolean requestModeration = true;

    @NotBlank
    @Size(min = MainCommon.MIN_LENGTH_TITLE, max = MainCommon.MAX_LENGTH_TITLE)
    private String title;
}
