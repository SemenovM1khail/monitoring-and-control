package ru.training.mc.core.api.services.monitoring.service.impl;

import org.junit.jupiter.api.Test;
import ru.training.mc.core.api.services.monitoring.service.impl.FakeMonitoringService;

import static org.junit.jupiter.api.Assertions.*;

public class FakeMonitoringServiceTest {

    FakeMonitoringService fakeMonitoringService =
            new FakeMonitoringService();

    @Test
    void getFakeTemperatureInC_getValidTemperature() {
        Float result = fakeMonitoringService
                .getTemperatureInC();
        assertNotNull(result);
        assertTrue(-20 <= result && result <= 30);
    }

    @Test
    void getFakeTemperatureInF_getValidTemperature() {
        Float result = fakeMonitoringService
                .getTemperatureInF();
        assertNotNull(result);
        assertTrue(0 <= result && result <= 100);
    }

    @Test
    void getFakePressureInPa_getValidPressure() {
        Float result = fakeMonitoringService
                .getPressureInPa();
        assertNotNull(result);
        assertTrue(70000 <= result && result <= 110000);
    }

    @Test
    void getFakePressureInMmHg_getValidPressure() {
        Float result = fakeMonitoringService
                .getPressureInMmHg();
        assertNotNull(result);
        assertTrue(600 <= result && result <= 800);

    }
}
