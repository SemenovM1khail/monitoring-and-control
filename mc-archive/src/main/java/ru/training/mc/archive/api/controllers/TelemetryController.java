package ru.training.mc.archive.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.archive.api.dto.factories.TelemetryResponseDtoFactory;
import ru.training.mc.archive.store.repositories.TelemetryRepository;

@RestController
@AllArgsConstructor
@RequestMapping("/api/archive/data/read")
public class TelemetryController {

    TelemetryRepository telemetryRepository;

    TelemetryResponseDtoFactory telemetryResponseDtoFactory;

    //TODO: реализовать
}
