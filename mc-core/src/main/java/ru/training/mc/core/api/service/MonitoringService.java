package ru.training.mc.core.api.service;

import org.springframework.stereotype.Service;

@Service
public interface MonitoringService {
    Float getTemperatureInC();

    Float getTemperatureInF();

    Float getPressureInMmHg();

    Float getPressureInPa();
}
