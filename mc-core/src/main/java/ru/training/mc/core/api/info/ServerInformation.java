package ru.training.mc.core.api.info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

//TODO: вынести в конфиг файл
@Getter
public class ServerInformation {

    @JsonIgnore
    private static ServerInformation INSTANCE = new ServerInformation();

    private final Long ID = 2L;

    private final String LOCATION = "Severodvinsk";

    private final String DESCRIPTION = "Test server";

    private ServerInformation() {
    }

    public static ServerInformation getInstance() {
        return INSTANCE;
    }

}