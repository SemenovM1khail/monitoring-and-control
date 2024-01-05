package ru.training.mc.archive.api.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.training.mc.archive.api.dto.ServerInfoDto;
import ru.training.mc.archive.api.dto.request.TelemetryRequestDto;
import ru.training.mc.archive.store.factories.ServerInfoEntityFactory;
import ru.training.mc.archive.store.factories.TelemetryEntityFactory;
import ru.training.mc.archive.store.repositories.ServerInfoRepository;
import ru.training.mc.archive.store.repositories.TelemetryRepository;

import java.util.Objects;

@Service
@Setter
@Getter
@AllArgsConstructor
@EnableScheduling
public class ArchiverService {

    private RestTemplateBuilder restTemplateBuilder;
    private TelemetryRepository telemetryRepository;
    private ServerInfoRepository serverInfoRepository;
    private TelemetryEntityFactory telemetryEntityFactory;
    private ServerInfoEntityFactory serverInfoEntityFactory;

    //TODO: вынести в конфиг
    private static final String CORE_ADDRESS_TELEMETRY
            = "http://mc-core:8080/api/monitor";

    private static final String CORE_ADDRESS_INFO
            = "http://mc-core:8080/api/info";


    @Scheduled(fixedRate = 5000)
    protected void scheduleWhetherSave() {
        try {
            TelemetryRequestDto telemetryRequestDto
                    = restTemplateBuilder
                    .build()
                    .getForObject(
                            CORE_ADDRESS_TELEMETRY,
                            TelemetryRequestDto.class);


            if (!serverInfoRepository
                    .existsById(Objects
                            .requireNonNull(telemetryRequestDto)
                            .getServerId())) {
                serverInfoRepository
                        .saveAndFlush(serverInfoEntityFactory
                                .makeServerInfoEntity(Objects
                                        .requireNonNull(restTemplateBuilder
                                                .build().getForObject(
                                                        CORE_ADDRESS_INFO,
                                                        ServerInfoDto.class))));
            }

            telemetryRepository
                    .saveAndFlush(telemetryEntityFactory
                            .makeTelemetryEntity(
                                    telemetryRequestDto));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
