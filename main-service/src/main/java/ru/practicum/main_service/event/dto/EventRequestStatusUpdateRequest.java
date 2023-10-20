package ru.practicum.main_service.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.practicum.main_service.event.enums.RequestStatusAction;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EventRequestStatusUpdateRequest {
    @NotEmpty
    private List<Long> requestIds;

    @NotNull
    private RequestStatusAction status;
}
