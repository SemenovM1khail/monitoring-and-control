package ru.training.mc.core.api.services.control.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value="api.modbus.connection.type", havingValue = "fake")
public class FakeControlService implements ControlService {

    private final Integer[] fakeRegisters = new Integer[4];

    @Override
    public void setRegisterValue(Integer address, Integer data) {
        fakeRegisters[address] = data;
    }

    @Override
    public Integer getRegisterValue(Integer address) {
        return fakeRegisters[address];
    }
}