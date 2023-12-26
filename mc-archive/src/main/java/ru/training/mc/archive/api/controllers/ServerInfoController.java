package ru.training.mc.archive.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.training.mc.archive.api.dto.factories.ServerInfoResponseDtoFactory;
import ru.training.mc.archive.api.dto.response.ServerInfoResponseDto;
import ru.training.mc.archive.store.repositories.ServerInfoRepository;

@RestController
@AllArgsConstructor
@RequestMapping("/api/archive/server/read")
public class ServerInfoController {

    ServerInfoRepository serverInfoRepository;

    ServerInfoResponseDtoFactory serverInfoResponseDtoFactory;

    //TODO: реализовать
}
