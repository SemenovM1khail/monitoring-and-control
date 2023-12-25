package ru.training.mc.core.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.core.api.dto.FloatDto;
import ru.training.mc.core.api.dto.TelemetryDto;
import ru.training.mc.core.api.services.monitoring.service.impl.MonitoringService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/monitor")
public class MonitorController {

    private final MonitoringService monitoringService;

    @GetMapping("/temperature/{unit}")
    public ResponseEntity<FloatDto> readTemperature(@PathVariable String unit) {
        return switch (unit.toLowerCase()) {
            case ("c") -> buildOkResponseWithValue(
                    monitoringService.getTemperatureInC());
            case ("f") -> buildOkResponseWithValue(
                    monitoringService.getTemperatureInF());
            default -> ResponseEntity.notFound().build();
        };
    }

    @GetMapping("/pressure/{unit}")
    public ResponseEntity<FloatDto> readPressure(@PathVariable String unit) {
        return switch (unit.toLowerCase()) {
            case ("mmhg") -> buildOkResponseWithValue(
                    monitoringService.getPressureInMmHg());
            case ("pa") -> buildOkResponseWithValue(
                    monitoringService.getPressureInPa());
            default -> ResponseEntity.notFound().build();
        };
    }

    @GetMapping
    public ResponseEntity<TelemetryDto> readCurrentData() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(TelemetryDto.builder()
                        .temperature(monitoringService
                                .getTemperatureInC())
                        .pressure(monitoringService
                                .getPressureInMmHg())
                        .build());
    };


    private ResponseEntity<FloatDto> buildOkResponseWithValue(Float value) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(FloatDto.builder()
                        .value(value)
                        .build());
    }
}