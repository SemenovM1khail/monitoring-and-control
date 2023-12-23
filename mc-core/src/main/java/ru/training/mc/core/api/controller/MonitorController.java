package ru.training.mc.core.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.core.api.service.monitoring.service.impl.MonitoringService;

@RestController
@RequestMapping("/api/monitor/current")
@AllArgsConstructor
public class MonitorController {

    private final MonitoringService monitoringService;

    @GetMapping("/temperature/{unit}")
    public ResponseEntity<Float> readTemperature(@PathVariable String unit) {
        return switch (unit.toLowerCase()) {
            case ("c") -> ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(monitoringService.getTemperatureInC());
            case ("f") -> ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(monitoringService.getTemperatureInF());
            default -> ResponseEntity.notFound().build();
        };
    }

    @GetMapping("/pressure/{unit}")
    public ResponseEntity<Float> readPressure(@PathVariable String unit) {
        return switch (unit.toLowerCase()) {
            case ("mmhg") -> ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(monitoringService.getPressureInMmHg());
            case ("pa") -> ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(monitoringService.getPressureInPa());
            default -> ResponseEntity.notFound().build();
        };
    }

}