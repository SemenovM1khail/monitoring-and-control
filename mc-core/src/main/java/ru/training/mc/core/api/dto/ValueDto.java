package ru.training.mc.core.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.training.mc.core.api.configuration.ServerConfiguration;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValueDto {

    @NonNull
    @Builder.Default
    @JsonProperty("server_id")
    private UUID serverId = ServerConfiguration.serverUUID;

    @NonNull
    private Float value;

}
