package ru.practicum.main_service.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.main_service.MainCommon;
import ru.practicum.main_service.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String text;
    private UserShortDto author;
    private Long eventId;

    @JsonFormat(pattern = MainCommon.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdOn;

    @JsonFormat(pattern = MainCommon.DT_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDateTime editedOn;
}
