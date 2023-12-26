package ru.training.mc.core.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.training.mc.core.api.dto.ValuesDto;
import ru.training.mc.core.api.services.monitoring.service.impl.MonitoringService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/monitor")
public class MonitorController {

    private final MonitoringService monitoringService;

    @GetMapping("/temperature/{unit}")
    public ResponseEntity<ValuesDto<?>> readTemperature(
            @PathVariable String unit) {
        return switch (unit.toLowerCase()) {
            case ("c") -> buildOkResponseWithValue(
                    monitoringService.getTemperatureInC());
            case ("f") -> buildOkResponseWithValue(
                    monitoringService.getTemperatureInF());
            default -> ResponseEntity.notFound().build();
        };
    }

    @GetMapping("/pressure/{unit}")
    public ResponseEntity<ValuesDto<?>> readPressure(
            @PathVariable String unit) {
        return switch (unit.toLowerCase()) {
            case ("mmhg") -> buildOkResponseWithValue(
                    monitoringService.getPressureInMmHg());
            case ("pa") -> buildOkResponseWithValue(
                    monitoringService.getPressureInPa());
            default -> ResponseEntity.notFound().build();
        };
    }

    @GetMapping
    public ResponseEntity<ValuesDto<?>> readCurrentData() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ValuesDto.builder()
                        .values(List.of(
                                monitoringService
                                        .getTemperatureInC(),
                                monitoringService
                                        .getPressureInMmHg()))
                        .build());
    }


    private ResponseEntity<ValuesDto<?>> buildOkResponseWithValue(
            Float value) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ValuesDto.builder()
                        .values(List.of(value))
                        .build());
    }
}