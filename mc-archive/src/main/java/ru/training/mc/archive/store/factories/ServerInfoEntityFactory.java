package ru.training.mc.archive.store.factories;

import org.springframework.stereotype.Component;
import ru.training.mc.archive.api.dto.request.ServerInfoDto;
import ru.training.mc.archive.store.entities.ServerInfoEntity;

@Component
public class ServerInfoEntityFactory {
    public ServerInfoEntity makeServerInfoEntity(
            ServerInfoDto serverInfoDto) {
        return ServerInfoEntity.builder()
                .id(serverInfoDto.getId())
                .location(serverInfoDto.getLocation())
                .description(serverInfoDto.getDescription())
                .build();
    }
}
