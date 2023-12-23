package ru.training.mc.core.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.core.api.dto.ValueDto;
import ru.training.mc.core.api.services.monitoring.service.impl.MonitoringService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/monitor/")
public class MonitorController {

    private final MonitoringService monitoringService;

    @GetMapping("/temperature/{unit}")
    public ResponseEntity<ValueDto> readTemperature(@PathVariable String unit) {
        return switch (unit.toLowerCase()) {
            case ("c") -> buildOkResponseWithValue(
                    monitoringService.getTemperatureInC());
            case ("f") -> buildOkResponseWithValue(
                    monitoringService.getTemperatureInF());
            default -> ResponseEntity.notFound().build();
        };
    }

    @GetMapping("/pressure/{unit}")
    public ResponseEntity<ValueDto> readPressure(@PathVariable String unit) {
        return switch (unit.toLowerCase()) {
            case ("mmhg") -> buildOkResponseWithValue(
                    monitoringService.getPressureInMmHg());
            case ("pa") -> buildOkResponseWithValue(
                    monitoringService.getPressureInPa());
            default -> ResponseEntity.notFound().build();
        };
    }

    private ResponseEntity<ValueDto> buildOkResponseWithValue(Float value) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ValueDto.builder()
                        .value(value)
                        .build());
    }
}