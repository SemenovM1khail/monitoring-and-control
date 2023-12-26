package ru.training.mc.archive.store.entities;

import jakarta.persistence.*;
import lombok.*;

import  ru.training.mc.archive.store.entities.ServerInfoEntity;

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

    @JoinColumn(
            name = "server_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    @ManyToOne(targetEntity = ServerInfoEntity.class,
            fetch = FetchType.EAGER)
    private ServerInfoEntity serverInfo;

    @Column(name = "server_id")
    private Long serverId;

    private Float temperature;

    private Float pressure;
}
