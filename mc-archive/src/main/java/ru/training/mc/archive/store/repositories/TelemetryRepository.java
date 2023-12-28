package ru.training.mc.archive.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.training.mc.archive.store.entities.TelemetryEntity;

import java.time.Instant;
import java.util.List;

@Repository
public interface TelemetryRepository
        extends JpaRepository<TelemetryEntity, Long> {

    List<TelemetryEntity> findAllByIdBetween(
            Long moreThen, Long lessThen);

    List<TelemetryEntity> findAllByServerId(Long serverId);

    List<TelemetryEntity> findAllByServerIdBetween(
            Long moreThen, Long lessThen);

    List<TelemetryEntity> findAllByCreatedAtBetween(
            Instant after, Instant before);

    List<TelemetryEntity> findAllByTemperatureBetween(
            Float moreThen, Float lessThen);

    List<TelemetryEntity> findAllByPressureBetween(
            Float moreThen, Float lessThen);

}
