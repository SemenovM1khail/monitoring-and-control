package ru.training.mc.core.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.core.api.service.control.service.impl.ControlService;

@RestController
@RequestMapping("/api/control")
@AllArgsConstructor
public class ControlController {

    private final ControlService controlService;

    @PutMapping
    public String setRegisterValue(@RequestParam("address") Integer address,
                                   @RequestParam("value") Integer value) {
        controlService.setRegisterValue(address, value);
        return value + "-> reg:" + address;
    }
}
