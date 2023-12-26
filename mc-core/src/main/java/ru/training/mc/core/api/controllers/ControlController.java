package ru.training.mc.core.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.core.api.dto.ValuesDto;
import ru.training.mc.core.api.services.control.service.impl.ControlService;

import java.util.List;

@RestController
@RequestMapping("/api/control")
@AllArgsConstructor
public class ControlController {

    private final ControlService controlService;

    @PutMapping
    public ResponseEntity<ValuesDto<?>> setRegisterValue(
            @RequestParam("address") Integer address,
            @RequestParam("value") Integer value) {
        controlService.setRegisterValue(address, value);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ValuesDto.builder()
                        .values(List.of(value))
                        .build());
    }

    @GetMapping("/read")
    public ResponseEntity<ValuesDto<?>> getRegisterValue(
            @RequestParam("address") Integer address) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ValuesDto.builder()
                        .values(List.of(controlService
                                .getRegisterValue(address)))
                        .build());
    }
}
