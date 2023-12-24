package ru.training.mc.core.api.services.control.service.impl;

public interface ControlService {
    void setRegisterValue(Integer address, Integer data);

    Integer getRegisterValue(Integer address);
}

