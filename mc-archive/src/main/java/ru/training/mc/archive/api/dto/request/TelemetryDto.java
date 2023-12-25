package ru.training.mc.archive.api.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryDto {

    @NonNull
    @JsonProperty("server_info")
    private ServerInfoDto serverInfo;

    @NonNull
    private Float temperature;

    @NonNull
    private Float pressure;
}
