package ru.training.mc.archive.api.dto.factories;

import org.springframework.stereotype.Component;
import ru.training.mc.archive.api.dto.ServerInfoDto;
import ru.training.mc.archive.store.entities.ServerInfoEntity;

@Component
public class ServerInfoDtoFactory {

    public ServerInfoDto makeServerResponseDtoFactory(
            ServerInfoEntity serverInfoEntity) {
        return ServerInfoDto.builder()
                .id(serverInfoEntity.getId())
                .location(serverInfoEntity.getLocation())
                .description(serverInfoEntity.getDescription())
                .build();
    }
}
