package ru.training.mc.core.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.training.mc.core.api.info.ServerInformation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerInfoDto {

    @Builder.Default
    private final Long id
            = ServerInformation
            .getInstance()
            .getID();

    @Builder.Default
    private final String location
            = ServerInformation
            .getInstance()
            .getLOCATION();

    @Builder.Default
    private final String description
            = ServerInformation
            .getInstance()
            .getDESCRIPTION();
}
