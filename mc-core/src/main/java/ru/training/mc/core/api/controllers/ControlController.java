package ru.training.mc.core.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.core.api.controllers.dto.IntegerDto;
import ru.training.mc.core.api.services.control.service.impl.ControlService;

@RestController
@RequestMapping("/api/control")
@AllArgsConstructor
public class ControlController {

    private final ControlService controlService;

    @PutMapping
    public ResponseEntity<IntegerDto> setRegisterValue(
            @RequestParam("address") Integer address,
            @RequestParam("value") Integer value) {
        controlService.setRegisterValue(address, value);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(IntegerDto.builder()
                        .value(value)
                        .build());
    }

    @GetMapping("/read")
    public ResponseEntity<IntegerDto> getRegisterValue(
            @RequestParam("address") Integer address) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(IntegerDto.builder()
                        .value(controlService
                                .getRegisterValue(address))
                        .build());
    }
}
