package ru.training.mc.core.api.services.control.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.training.mc.core.api.services.modbus.facade.ModbusFacade;

@Service
@AllArgsConstructor
@ConditionalOnProperty(value="api.modbus.connection.type", havingValue = "real")
public class ModbusControlService implements ControlService {

    ModbusFacade modbusFacade;
    @Override
    public void setRegisterValue(Integer address, Integer data) {
        modbusFacade.writeRegister(address, data);
    }
}
