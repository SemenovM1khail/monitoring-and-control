package ru.training.mc.archive.store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.apache.catalina.util.ServerInfo;
import ru.training.mc.archive.api.dto.ServerInfoDto;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Builder.Default
    private Instant createdAt = Instant.now();

//    private ServerInfoDto serverInfo;

    private Float temperature;

    private Float pressure;
}
