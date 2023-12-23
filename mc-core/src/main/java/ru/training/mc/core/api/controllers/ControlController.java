package ru.training.mc.core.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.core.api.dto.ValueDto;
import ru.training.mc.core.api.services.control.service.impl.ControlService;

@RestController
@RequestMapping("/api/control")
@AllArgsConstructor
public class ControlController {

    private final ControlService controlService;

    @PutMapping
    public ResponseEntity<ValueDto> setRegisterValue(
            @RequestParam("address") Integer address,
            @RequestParam("value") Integer value) {
        controlService.setRegisterValue(address, value);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ValueDto.builder()
                        .value(Float.valueOf(value))
                        .build());
    }
}
