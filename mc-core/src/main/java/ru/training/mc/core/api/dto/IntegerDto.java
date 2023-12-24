package ru.training.mc.core.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.training.mc.core.api.info.ServerInformation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntegerDto {

    @NonNull
    @Builder.Default
    @JsonProperty("server_info")
    private ServerInformation serverInfo = ServerInformation.getInstance();

    @NonNull
    private Integer value;

}
