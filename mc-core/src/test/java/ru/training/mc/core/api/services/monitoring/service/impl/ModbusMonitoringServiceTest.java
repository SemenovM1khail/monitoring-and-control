package ru.training.mc.core.api.services.monitoring.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.training.mc.core.api.services.modbus.facade.ModbusFacade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ModbusMonitoringServiceTest {

    @Mock
    ModbusFacade modbusFacade;

    @InjectMocks
    ModbusMonitoringService modbusMonitoringService;

    @Test
    void getFakeTemperatureInC_getValidTemperature() {
        float mockedTemperature = 30.2f;
        doReturn((int) (mockedTemperature * 100))
                .when(modbusFacade)
                .readRegister(Mockito.anyInt());
        Float result = modbusMonitoringService
                .getTemperatureInC();
        assertNotNull(result);
        assertEquals(mockedTemperature, result, 0.1f);
    }

    @Test
    void getFakeTemperatureInF_getValidTemperature() {
        float mockedTemperature = 80.2f;
        doReturn((int) ((mockedTemperature - 32) / 0.018f))
                .when(modbusFacade)
                .readRegister(Mockito.anyInt());
        Float result = modbusMonitoringService
                .getTemperatureInF();
        assertNotNull(result);
        assertEquals(mockedTemperature, result, 0.1f);
    }

    @Test
    void getFakePressureInPa_getValidPressure() {
        float mockedPressure = 100_000f;
        doReturn((int) (mockedPressure / 10f))
                .when(modbusFacade)
                .readRegister(Mockito.anyInt());
        Float result = modbusMonitoringService
                .getPressureInPa();
        assertNotNull(result);
        assertEquals(mockedPressure, result, 0.1f);
    }

    @Test
    void getFakePressureInMmHg_getValidPressure() {
        float mockedPressure = 747f;
        doReturn((int) (mockedPressure / 0.075f))
                .when(modbusFacade)
                .readRegister(Mockito.anyInt());
        Float result = modbusMonitoringService
                .getPressureInMmHg();
        assertNotNull(result);
        assertEquals(mockedPressure, result, 0.1f);

    }
}
