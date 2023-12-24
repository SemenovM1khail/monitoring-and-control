package ru.training.mc.core.api.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
public class ServerInformation {

    @JsonIgnore
    private static ServerInformation INSTANCE = new ServerInformation();

    private final Long ID = 1L;

    private final String LOCATION = "Moscow";

    private final String DESCRIPTION = "Modbus master connected to arduino (modbus slave)";

    private ServerInformation(){}

    public static ServerInformation getInstance() {
        return INSTANCE;
    }

}