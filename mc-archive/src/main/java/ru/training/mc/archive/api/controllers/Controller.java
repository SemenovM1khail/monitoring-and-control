package ru.training.mc.archive.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.archive.store.TelemetryRepository;


@RestController
@AllArgsConstructor
@RequestMapping("/api/archive/read")
public class Controller {

//    private TelemetryDtoFactory telemetryDtoFactory;

    private TelemetryRepository telemetryRepository;

}
