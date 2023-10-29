package ru.practicum.main_service.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.main_service.MainCommon;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCommentDto {
    @NotBlank
    @Size(min = MainCommon.MIN_LENGTH_COMMENT, max = MainCommon.MAX_LENGTH_COMMENT)
    private String text;
}
