package ru.training.mc.core.api.service.monitoring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.training.mc.core.api.service.modbus.facade.ModbusFacade;

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
        return modbusFacade.readRegister(5) * 0.75f;
    }

    @Override
    public Float getPressureInPa() {
        return modbusFacade.readRegister(5) * 100f;
    }
}
