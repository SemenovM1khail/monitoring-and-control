package ru.training.mc.core.api.service.modbus.facade;

import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.SerialParameters;
import com.intelligt.modbus.jlibmodbus.serial.SerialPortFactoryPJC;
import com.intelligt.modbus.jlibmodbus.serial.SerialUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ModbusFacade implements DisposableBean {
    private ModbusMaster modBus;
    private final Integer slaveAddress;


    ModbusFacade(@Value("${api.modbus.portname}") String portName,
                 @Value("${api.modbus.device.address}") Integer slaveAddress) {
        this.slaveAddress = slaveAddress;
        try {
            SerialUtils.setSerialPortFactory(new SerialPortFactoryPJC());
            SerialParameters sp = new SerialParameters();
            sp.setDevice(portName);
            modBus = ModbusMasterFactory.createModbusMasterRTU(sp);
            modBus.connect();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer readRegister(Integer registerAddress) {

        Integer registerValue = null;

        try {
            registerValue = modBus.readHoldingRegisters(
                    slaveAddress, registerAddress, 1)[0];
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return registerValue;
    }

    public void writeRegister(int address, int data) {
        try {
            modBus.writeSingleRegister(slaveAddress, address, data);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        try {
            modBus.disconnect();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
