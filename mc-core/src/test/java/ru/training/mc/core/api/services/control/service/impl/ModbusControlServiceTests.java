package ru.training.mc.core.api.services.control.service.impl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.training.mc.core.api.services.modbus.facade.ModbusFacade;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModbusControlServiceTests {

    @Mock
    ModbusFacade modbusFacade;

    @InjectMocks
    ModbusControlService modbusControlService;

    @Test
    void setRegisters_GetValidRegistersValue() {
        int registersQuantity = 4;
        int registerValue = 2 * Short.MAX_VALUE;
        int[] inMemoryRegisters = new int[registersQuantity];
        doAnswer(invocation -> {
            inMemoryRegisters[invocation
                    .getArgument(0, Integer.class)]
                    = invocation.getArgument(1, Integer.class);
            return null;})
                .when(modbusFacade)
                .writeRegister(Mockito.anyInt(), Mockito.anyInt());
        doAnswer(invocation ->
                inMemoryRegisters[invocation
                        .getArgument(0, Integer.class)])
                .when(modbusFacade)
                .readRegister(Mockito.anyInt());

        IntStream.range(0, registersQuantity)
                .forEach(index -> modbusControlService
                        .setRegisterValue(index, registerValue));

        IntStream.range(0, registersQuantity)
                .forEach(index -> assertAll(
                        () -> assertNotNull(modbusControlService
                                .getRegisterValue(index)),
                        () -> assertEquals(modbusControlService
                                .getRegisterValue(index), registerValue)
                ));
    }
}
