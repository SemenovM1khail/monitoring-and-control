package ru.training.mc.core.api.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

public class ServerConfiguration {

    static final public UUID serverUUID = UUID.randomUUID();

}
