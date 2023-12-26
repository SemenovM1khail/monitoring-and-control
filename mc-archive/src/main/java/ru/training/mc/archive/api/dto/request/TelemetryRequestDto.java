package ru.training.mc.archive.api.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryRequestDto {

    @NonNull
    @JsonProperty("server_id")
    private Long serverId;

    @NonNull
    private List<Float> values;
}
