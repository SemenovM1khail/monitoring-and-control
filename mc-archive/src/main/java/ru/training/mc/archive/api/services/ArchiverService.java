package ru.training.mc.archive.api.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.training.mc.archive.api.dto.request.TelemetryDto;
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

    private ServerInfoEntityFactory serverInfoEntityFactory;

    private TelemetryEntityFactory telemetryEntityFactory;

    //TODO: вынести в конфиг
    private static final String CORE_ADDRESS
            = "http://localhost:8082/api/monitor";

    @Scheduled(fixedRate = 5000)
    protected void scheduleWhetherSave() {
        try {
            TelemetryDto requestTelemetryDto = restTemplateBuilder.build()
                    .getForObject(CORE_ADDRESS,
                            TelemetryDto.class);


            if(!serverInfoRepository.existsById(Objects
                    .requireNonNull(requestTelemetryDto)
                    .getServerInfo()
                    .getId())) {
                serverInfoRepository
                        .saveAndFlush(
                                serverInfoEntityFactory
                                        .makeServerInfoEntity(
                                                requestTelemetryDto
                                                        .getServerInfo()
                                        )
                        );
            }

            telemetryRepository
                    .saveAndFlush(
                            telemetryEntityFactory
                                    .makeTelemetryEntity(
                                            requestTelemetryDto)
                    );

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
