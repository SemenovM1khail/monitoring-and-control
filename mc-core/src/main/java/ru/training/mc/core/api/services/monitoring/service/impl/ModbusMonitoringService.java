package ru.training.mc.core.api.services.monitoring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.training.mc.core.api.services.modbus.facade.ModbusFacade;

@Service
@AllArgsConstructor
@ConditionalOnProperty(value="api.modbus.connection.type", havingValue = "real")
public class ModbusMonitoringService implements MonitoringService {

    ModbusFacade modbusFacade;

    @Override
    public Float getTemperatureInC() {
        return modbusFacade.readRegister(4) / 100f;
    }

    @Override
    public Float getTemperatureInF() {
        return modbusFacade.readRegister(4) * 0.018f + 32;
    }

    @Override
    public Float getPressureInMmHg() {
        return modbusFacade.readRegister(5) * 0.075f;
    }

    @Override
    public Float getPressureInPa() {
        return modbusFacade.readRegister(5) * 10f;
    }
}
