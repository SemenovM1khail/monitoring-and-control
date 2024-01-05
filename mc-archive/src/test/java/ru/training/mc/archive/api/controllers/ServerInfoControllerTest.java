package ru.training.mc.archive.api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.training.mc.archive.api.dto.ServerInfoDto;
import ru.training.mc.archive.api.dto.factories.ServerInfoDtoFactory;
import ru.training.mc.archive.store.entities.ServerInfoEntity;
import ru.training.mc.archive.store.repositories.ServerInfoRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServerInfoControllerTest {

    @Mock
    ServerInfoDtoFactory serverInfoDtoFactory;

    @Mock
    ServerInfoRepository serverInfoRepository;

    @InjectMocks
    ServerInfoController serverInfoController;

    @Test
    void readServerInfoById_ReturnsValidResponse() {
        Long ID = 1L;

        ServerInfoEntity testEntity = new ServerInfoEntity(ID, "Test", "test");

        when(serverInfoDtoFactory
                .makeServerInfoDto(Mockito.any(ServerInfoEntity.class)))
                .thenAnswer(invocation -> {
                    ServerInfoEntity entity = invocation.getArgument(0);
                    return new ServerInfoDto(
                            entity.getId(),
                            entity.getLocation(),
                            entity.getDescription());
                });

        when(serverInfoRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(testEntity));

        var response = serverInfoController.readServerInfoById(ID);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        var testDto = new ServerInfoDto(ID, "Test", "test");
        assertEquals(testDto, response.getBody());
        verify(serverInfoRepository).findById(ID);
    }

    @Test
    void readServerInfoById_ReturnsNotFound() {
        ResponseEntity<ServerInfoDto> response =
                serverInfoController.readServerInfoById(1337L);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void readAllServerInfo_ReturnsValidResponses() {
        Long ID = 1L;

        ServerInfoEntity testEntity = new ServerInfoEntity(ID, "Test", "test");

        when(serverInfoDtoFactory
                .makeServerInfoDto(Mockito.any(ServerInfoEntity.class)))
                .thenAnswer(invocation -> {
                    ServerInfoEntity entity = invocation.getArgument(0);
                    return new ServerInfoDto(entity.getId(), entity.getLocation(), entity.getDescription());
                });

        when(serverInfoRepository.findAll())
                .thenReturn(List.of(testEntity));

        var response = serverInfoController.readAllServerInfo();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        var testDto = new ServerInfoDto(ID, "Test", "test");
        assertEquals(1, response.getBody().size());
        assertEquals(List.of(testDto), response.getBody());
    }

    @Test
    void readAllServerInfo_ReturnsEmptyResponses() {
        ResponseEntity<List<ServerInfoDto>> response =
                serverInfoController.readAllServerInfo();

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(Objects.requireNonNull(response.getBody()).isEmpty());
    }
}