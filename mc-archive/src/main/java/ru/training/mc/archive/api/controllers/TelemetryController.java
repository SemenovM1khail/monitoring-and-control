package ru.training.mc.archive.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.archive.api.dto.factories.TelemetryResponseDtoFactory;
import ru.training.mc.archive.api.dto.response.TelemetryResponseDto;
import ru.training.mc.archive.store.entities.TelemetryEntity;
import ru.training.mc.archive.store.repositories.TelemetryRepository;

import java.time.Instant;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/archive/data/read")
public class TelemetryController {

    TelemetryRepository telemetryRepository;

    TelemetryResponseDtoFactory telemetryResponseDtoFactory;

    @GetMapping
    public ResponseEntity<TelemetryResponseDto> readServerInfoById(
            @RequestParam("id") Long id) {
        return telemetryRepository
                .findById(id)
                .map(telemetryEntity ->
                        ResponseEntity.ok()
                                .body(telemetryResponseDtoFactory.makeTelemetryResponseDto(
                                                telemetryEntity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<TelemetryResponseDto>> readAllServerInfo() {
        return ResponseEntity.ok()
                .body(telemetryRepository
                        .findAll()
                        .stream()
                        .map(telemetryEntity ->
                                telemetryResponseDtoFactory
                                        .makeTelemetryResponseDto(
                                                telemetryEntity))
                        .toList());
    }

    //TODO: Выборка для конкретного сервера
    //TODO: Сортировка при фильтрации

    @GetMapping("/filter/id")
    public ResponseEntity<List<TelemetryResponseDto>> readAllWithIdBetween(
            @RequestParam("more") Long moreThen, @RequestParam("less") Long lessThen) {
        return buildResponseFromEntityList(
                telemetryRepository
                        .findAllByIdBetween(moreThen, lessThen));
    }

    @GetMapping("/filter/server-id")
    public ResponseEntity<List<TelemetryResponseDto>> readAllWithServerIdBetween(
            @RequestParam("more") Long moreThen, @RequestParam("less") Long lessThen) {
        return buildResponseFromEntityList(
                telemetryRepository
                        .findAllByServerIdBetween(moreThen, lessThen));
    }

    @GetMapping("/filter/temperature")
    public ResponseEntity<List<TelemetryResponseDto>> readAllWithTempBetween(
            @RequestParam("more") Float moreThen, @RequestParam("less") Float lessThen) {
        return buildResponseFromEntityList(
                telemetryRepository
                        .findAllByTemperatureBetween(
                                moreThen, lessThen));
    }

    @GetMapping("/filter/pressure")
    public ResponseEntity<List<TelemetryResponseDto>> readAllWithPressureBetween(
            @RequestParam("more") Float moreThen, @RequestParam("less") Float lessThen) {
        return buildResponseFromEntityList(
                telemetryRepository
                        .findAllByPressureBetween(moreThen, lessThen));
    }

    @GetMapping("/filter/created_at")
    public ResponseEntity<List<TelemetryResponseDto>> readAllWithCreatedAtBetween(
            @RequestParam("after") Instant after, @RequestParam("before") Instant between) {
        return buildResponseFromEntityList(
                telemetryRepository
                        .findAllByCreatedAtBetween(after, between));
    }

    private ResponseEntity<List<TelemetryResponseDto>> buildResponseFromEntityList(
            List<TelemetryEntity> telemetryEntityList) {
        return ResponseEntity.ok()
                .body(telemetryEntityList
                        .stream()
                        .map(telemetryEntity ->
                                telemetryResponseDtoFactory
                                        .makeTelemetryResponseDto(
                                                telemetryEntity))
                        .toList());
    }

}
