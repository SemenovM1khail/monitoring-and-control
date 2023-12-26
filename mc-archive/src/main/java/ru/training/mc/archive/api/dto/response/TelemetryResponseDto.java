package ru.training.mc.archive.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class TelemetryResponseDto {

    @NonNull
    private Long id;

    @NonNull
    @JsonProperty("created_at")
    private Instant createdAt;

    @NonNull
    @JsonProperty("server_id")
    private Long serverId;

    @NonNull
    private Float temperature;

    @NonNull
    private Float pressure;
}
