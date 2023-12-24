package ru.training.mc.core.api.services.control.service.impl;

import org.junit.jupiter.api.Test;
import ru.training.mc.core.api.services.control.service.impl.FakeControlService;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class FakeControlServiceTest {

    FakeControlService fakeControlService = new FakeControlService();

    @Test
    void setRegisters_GetValidRegistersValue() {
        int registersQuantity = 4;
        int registerValue = 2 * Short.MAX_VALUE;
        IntStream.range(0, registersQuantity)
                .forEach(index -> fakeControlService
                        .setRegisterValue(index, registerValue));

        IntStream.range(0, registersQuantity)
                .forEach(index -> assertAll(
                        () -> assertNotNull(fakeControlService.getRegisterValue(index)),
                        () -> assertEquals(fakeControlService.getRegisterValue(index), registerValue)
                ));
    }
}
