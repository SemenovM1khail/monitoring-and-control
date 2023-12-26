package ru.training.mc.archive.api.dto.factories;

import org.springframework.stereotype.Component;
import ru.training.mc.archive.api.dto.response.TelemetryResponseDto;
import ru.training.mc.archive.store.entities.TelemetryEntity;

@Component
public class TelemetryResponseDtoFactory {

    public TelemetryResponseDto makeTelemetryResponseDto(
            TelemetryEntity telemetryEntity) {
        return TelemetryResponseDto.builder()
                .id(telemetryEntity.getId())
                .createdAt(telemetryEntity.getCreatedAt())
                .serverId(telemetryEntity.getServerId())
                .temperature(telemetryEntity.getTemperature())
                .pressure(telemetryEntity.getPressure())
                .build();
    }
}
