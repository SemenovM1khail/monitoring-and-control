package ru.training.mc.archive.store.factories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.training.mc.archive.api.dto.request.TelemetryDto;
import ru.training.mc.archive.store.entities.TelemetryEntity;

@Component
@AllArgsConstructor
public class TelemetryEntityFactory {

    private ServerInfoEntityFactory serverInfoEntityFactory;
    public TelemetryEntity makeTelemetryEntity(TelemetryDto telemetryDto) {
        return TelemetryEntity.builder()
                .serverInfo(serverInfoEntityFactory
                        .makeServerInfoEntity(
                                telemetryDto.getServerInfo()))
                .temperature(telemetryDto.getTemperature())
                .pressure(telemetryDto.getPressure())
                .build();
    }
}
