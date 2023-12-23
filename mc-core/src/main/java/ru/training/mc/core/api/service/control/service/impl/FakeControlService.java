package ru.training.mc.core.api.service.control.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value="api.modbus.connection.type", havingValue = "fake")
public class FakeControlService implements ControlService {

    Integer[] fakeRegisters = new Integer[4];

    @Override
    public void setRegisterValue(Integer address, Integer data) {
        fakeRegisters[address] = data;
    }
}
