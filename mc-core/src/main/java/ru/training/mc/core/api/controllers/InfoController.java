package ru.training.mc.core.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.training.mc.core.api.dto.ServerInfoDto;

@RestController
@RequestMapping("/api/server-info")
@AllArgsConstructor
public class InfoController {

    @GetMapping
    public ResponseEntity<ServerInfoDto> getInfo() {
        return ResponseEntity.ok().body(
                ServerInfoDto
                        .builder().build());
    }
}
