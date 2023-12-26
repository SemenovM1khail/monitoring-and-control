package ru.training.mc.archive.store.factories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.training.mc.archive.api.dto.request.TelemetryRequestDto;
import ru.training.mc.archive.store.entities.TelemetryEntity;

@Component
@AllArgsConstructor
public class TelemetryEntityFactory {

    public TelemetryEntity makeTelemetryEntity(TelemetryRequestDto telemetryRequestDto) {
        return TelemetryEntity.builder()
                .serverId(telemetryRequestDto.getServerId())
                .temperature(telemetryRequestDto.getValues().get(0))
                .pressure(telemetryRequestDto.getValues().get(1))
                .build();
    }
}
