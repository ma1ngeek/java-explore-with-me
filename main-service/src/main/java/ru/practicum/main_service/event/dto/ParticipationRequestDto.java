package ru.practicum.main_service.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.main_service.MainCommon;
import ru.practicum.main_service.event.enums.RequestStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipationRequestDto {
    private Long id;
    private Long event;
    private Long requester;

    @JsonFormat(pattern = MainCommon.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    private RequestStatus status;
}
