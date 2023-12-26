package ru.training.mc.core.api.info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

//TODO: вынести в конфиг файл
@Getter
public class ServerInformation {

    @JsonIgnore
    private static ServerInformation INSTANCE = new ServerInformation();

    private final Long ID = 1L;

    private final String LOCATION = "Moscow";

    private final String DESCRIPTION = "Modbus master connected to arduino (modbus slave)";

    private ServerInformation() {
    }

    public static ServerInformation getInstance() {
        return INSTANCE;
    }

}