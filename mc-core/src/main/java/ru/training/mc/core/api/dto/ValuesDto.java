package ru.training.mc.core.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.training.mc.core.api.info.ServerInformation;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValuesDto<T> {

    @NonNull
    @Builder.Default
    @JsonProperty("server_id")
    private Long serverId =
            ServerInformation
                    .getInstance()
                    .getID();

    @NonNull
    private List<T> values;
}

