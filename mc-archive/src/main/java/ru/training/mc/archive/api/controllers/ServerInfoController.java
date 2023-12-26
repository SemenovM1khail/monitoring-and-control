package ru.training.mc.archive.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.training.mc.archive.api.dto.ServerInfoDto;
import ru.training.mc.archive.api.dto.factories.ServerInfoDtoFactory;
import ru.training.mc.archive.store.repositories.ServerInfoRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/archive/server/read")
public class ServerInfoController {

    ServerInfoRepository serverInfoRepository;

    ServerInfoDtoFactory serverInfoDtoFactory;

    @GetMapping
    public ResponseEntity<ServerInfoDto> readServerInfoById(
            @RequestParam("id") Long id) {
        return serverInfoRepository
                .findById(id)
                .map(serverInfoEntity ->
                        ResponseEntity.ok()
                                .body(serverInfoDtoFactory
                                        .makeServerInfoDtoFactory(
                                                serverInfoEntity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServerInfoDto>> readAllServerInfo() {
        return ResponseEntity.ok()
                .body(serverInfoRepository
                        .findAll()
                        .stream()
                        .map(serverInfoEntity ->
                                serverInfoDtoFactory
                                        .makeServerInfoDtoFactory(
                                                serverInfoEntity))
                        .toList());
    }
}
