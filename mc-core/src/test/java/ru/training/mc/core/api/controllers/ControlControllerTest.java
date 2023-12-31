package ru.training.mc.core.api.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.training.mc.core.api.dto.ValuesDto;
import ru.training.mc.core.api.services.control.service.impl.ControlService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ControlControllerTest {

    @Mock
    ControlService controlService;

    @InjectMocks
    ControlController controlController;

    @Test
    void setRegisterValue_ReturnValidResponseEntity() {
        int registersQuantity = 4;
        int registerValue = Short.MAX_VALUE * 2;
        int[] inMemoryRegisters = new int[registersQuantity];
        int[] arrayForComparison = new int[registersQuantity];
        Arrays.fill(arrayForComparison, registerValue);
        doAnswer(invocation -> {
            inMemoryRegisters[invocation
                    .getArgument(0, Integer.class)]
                    = invocation.getArgument(1, Integer.class);
            return null;
        })
                .when(controlService)
                .setRegisterValue(Mockito.anyInt(), Mockito.anyInt());
        List<ResponseEntity<ValuesDto<?>>> responseEntityList
                = new ArrayList<>();

        IntStream.range(0, registersQuantity)
                .forEach(index -> responseEntityList
                        .add(controlController
                                .setRegisterValue(
                                        index, registerValue)));

        responseEntityList.forEach(Assertions::assertNotNull);
        responseEntityList.forEach(responseEntity ->
                assertEquals(Objects.
                                requireNonNull(responseEntity
                                        .getBody())
                                .getValues().get(0),
                        registerValue));
        responseEntityList.forEach(
                responseEntity -> assertEquals(
                        responseEntity.getStatusCode(),
                        HttpStatus.OK));
        assertEquals(registersQuantity, responseEntityList.size());
        assertArrayEquals(arrayForComparison,
                inMemoryRegisters);
    }

    @Test
    void getRegisterValue_ReturnValidResponseEntity() {
        int registersQuantity = 4;
        int registerValue = Short.MAX_VALUE * 2;
        doReturn(registerValue)
                .when(controlService)
                .getRegisterValue(Mockito.anyInt());

        List<ResponseEntity<ValuesDto<?>>> responseEntityList
                = new ArrayList<>();
        IntStream.range(0, registersQuantity)
                .forEach(index -> responseEntityList
                        .add(controlController
                                .getRegisterValue(index)));

        responseEntityList.forEach(Assertions::assertNotNull);
        responseEntityList.forEach(responseEntity ->
                assertEquals(Objects
                                .requireNonNull(responseEntity
                                        .getBody())
                                .getValues().get(0),
                        registerValue));
        responseEntityList.forEach(
                responseEntity -> assertEquals(
                        responseEntity.getStatusCode(),
                        HttpStatus.OK));
        assertEquals(registersQuantity, responseEntityList.size());

    }
}