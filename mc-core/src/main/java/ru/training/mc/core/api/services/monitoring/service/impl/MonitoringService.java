package ru.training.mc.core.api.services.monitoring.service.impl;

import org.springframework.stereotype.Service;

@Service
public interface MonitoringService {
    Float getTemperatureInC();

    Float getTemperatureInF();

    Float getPressureInMmHg();

    Float getPressureInPa();
}
