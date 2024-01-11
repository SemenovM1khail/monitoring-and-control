package ru.training.mc.archive.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.training.mc.archive.api.dto.ServerInfoDto;
import ru.training.mc.archive.api.dto.factories.ServerInfoDtoFactory;
import ru.training.mc.archive.store.repositories.ServerInfoRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/archive/server")
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
                                        .makeServerInfoDto(
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
                                        .makeServerInfoDto(
                                                serverInfoEntity))
                        .toList());
    }
}
