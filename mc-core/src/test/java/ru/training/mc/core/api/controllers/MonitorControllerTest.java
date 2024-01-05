package ru.training.mc.core.api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.training.mc.core.api.services.monitoring.service.impl.MonitoringService;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class MonitorControllerTest {

    @Mock
    MonitoringService monitoringService;

    @InjectMocks
    MonitorController monitorController;

    @Test
    void readTemperature_inCelsius_ReturnsValidResponse() {
        Float mockedTemperature = 30.2f;
        doReturn(mockedTemperature).when(monitoringService)
                .getTemperatureInC();

        var responseEntity = monitorController
                .readTemperature("C");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(),
                HttpStatus.OK);
        assertEquals(Objects
                        .requireNonNull(responseEntity
                                .getBody())
                        .getValues()
                        .get(0),
                mockedTemperature);
    }

    @Test
    void readTemperature_inFahrenheit_ReturnsValidResponse() {
        Float mockedTemperature = 80.2f;
        doReturn(mockedTemperature).when(monitoringService)
                .getTemperatureInF();

        var responseEntity = monitorController
                .readTemperature("F");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(),
                HttpStatus.OK);
        assertEquals(Objects
                        .requireNonNull(
                                responseEntity
                                        .getBody())
                        .getValues()
                        .get(0),
                mockedTemperature);
    }

    @Test
    void readTemperature_badRequestPath_ReturnsNotFound() {

        var responseEntity = monitorController
                .readTemperature("wrongPath");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(),
                HttpStatus.NOT_FOUND);
    }

    @Test
    void readPressure_inPa_ReturnsValidResponse() {
        Float mockedPressure = 100000f;
        doReturn(mockedPressure).when(monitoringService)
                .getPressureInPa();

        var responseEntity = monitorController
                .readPressure("Pa");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(),
                HttpStatus.OK);
        assertEquals(Objects
                        .requireNonNull(
                                responseEntity
                                        .getBody())
                        .getValues()
                        .get(0),
                mockedPressure);
    }

    @Test
    void readPressure_inMmHg_ReturnsValidResponse() {
        Float mockedPressure = 747.5f;
        doReturn(mockedPressure).when(monitoringService)
                .getPressureInMmHg();

        var responseEntity = monitorController
                .readPressure("mmHg");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects
                        .requireNonNull(
                                responseEntity
                                        .getBody())
                        .getValues()
                        .get(0),
                mockedPressure);
    }

    @Test
    void readCurrentData_ReturnsValidResponse() {
        Float mockedTemperature = 30.2f;
        Float mockedPressure = 747.5f;
        doReturn(mockedTemperature).when(monitoringService)
                .getTemperatureInC();
        doReturn(mockedPressure).when(monitoringService)
                .getPressureInMmHg();

        var responseEntity = monitorController
                .readCurrentData();

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(
                                responseEntity.getBody())
                        .getValues()
                        .get(1),
                mockedPressure);
        assertEquals(Objects.requireNonNull(
                                responseEntity.getBody())
                        .getValues()
                        .get(0),
                mockedTemperature);
    }

    @Test
    void readPressure_badRequestPath_ReturnsNotFound() {
        var responseEntity = monitorController
                .readPressure("wrongPath");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(),
                HttpStatus.NOT_FOUND);
    }
}
