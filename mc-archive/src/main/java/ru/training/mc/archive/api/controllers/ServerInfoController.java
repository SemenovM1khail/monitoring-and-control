package ru.training.mc.archive.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.training.mc.archive.api.dto.factories.ServerInfoDtoFactory;
import ru.training.mc.archive.store.repositories.ServerInfoRepository;

@RestController
@AllArgsConstructor
@RequestMapping("/api/archive/server/read")
public class ServerInfoController {

    ServerInfoRepository serverInfoRepository;

    ServerInfoDtoFactory serverInfoDtoFactory;

    //TODO: реализовать
}
