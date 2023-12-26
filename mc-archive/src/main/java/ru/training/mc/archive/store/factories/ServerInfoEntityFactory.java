package ru.training.mc.archive.store.factories;

import org.springframework.stereotype.Component;
import ru.training.mc.archive.api.dto.request.ServerInfoRequestDto;
import ru.training.mc.archive.store.entities.ServerInfoEntity;

@Component
public class ServerInfoEntityFactory {
    public ServerInfoEntity makeServerInfoEntity(
            ServerInfoRequestDto serverInfoRequestDto) {
        return ServerInfoEntity.builder()
                .id(serverInfoRequestDto.getId())
                .location(serverInfoRequestDto.getLocation())
                .description(serverInfoRequestDto.getDescription())
                .build();
    }
}
