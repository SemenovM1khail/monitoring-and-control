package ru.training.mc.core.api.services.monitoring.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@ConditionalOnProperty(value = "api.modbus.connection.type", havingValue = "fake")
public class FakeMonitoringService implements MonitoringService {

    final Random random = new Random();

    @Override
    public Float getTemperatureInC() {
        return 50 * random.nextFloat() - 20;
    }

    @Override
    public Float getTemperatureInF() {
        return 100 * random.nextFloat();
    }

    @Override
    public Float getPressureInMmHg() {
        return 600 + 200 * random.nextFloat();
    }

    @Override
    public Float getPressureInPa() {
        return 70000 + 40000 * random.nextFloat();
    }
}

