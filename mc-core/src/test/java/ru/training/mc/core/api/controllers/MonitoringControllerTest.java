package ru.training.mc.core.api.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.training.mc.core.api.dto.FloatDto;
import ru.training.mc.core.api.services.monitoring.service.impl.MonitoringService;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class MonitoringControllerTest {

    @Mock
    MonitoringService monitoringService;

    @InjectMocks
    MonitorController monitorController;

    @Test
    void readTemperature_inCelsius_ReturnsValidResponse() {
        Float mockedTemperature = 30.2f;
        doReturn(mockedTemperature).when(monitoringService).getTemperatureInC();

        ResponseEntity<FloatDto> responseEntity
                = monitorController.readTemperature("C");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects
                .requireNonNull(responseEntity.getBody()).getValue(),
                                mockedTemperature);
    }

    @Test
    void readTemperature_inFahrenheit_ReturnsValidResponse() {
        Float mockedTemperature = 80.2f;
        doReturn(mockedTemperature).when(monitoringService).getTemperatureInF();

        ResponseEntity<FloatDto> responseEntity
                = monitorController.readTemperature("F");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects
                .requireNonNull(responseEntity.getBody()).getValue(),
                                mockedTemperature);
    }

    @Test
    void readTemperature_badRequestPath_ReturnsNotFound() {

        ResponseEntity<FloatDto> responseEntity
                = monitorController.readTemperature("wrongPath");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void readPressure_inPa_ReturnsValidResponse() {
        Float mockedPressure = 100000f;
        doReturn(mockedPressure).when(monitoringService).getPressureInPa();

        ResponseEntity<FloatDto> responseEntity
                = monitorController.readPressure("Pa");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects
                .requireNonNull(responseEntity.getBody()).getValue(),
                                mockedPressure);
    }

    @Test
    void readPressure_inMmHg_ReturnsValidResponse() {
        Float mockedPressure = 747.5f;
        doReturn(mockedPressure).when(monitoringService).getPressureInMmHg();

        ResponseEntity<FloatDto> responseEntity
                = monitorController.readPressure("mmHg");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects
                        .requireNonNull(responseEntity.getBody()).getValue(),
                mockedPressure);
    }

    @Test
    void readPressure_badRequestPath_ReturnsNotFound() {

        ResponseEntity<FloatDto> responseEntity
                = monitorController.readPressure("wrongPath");

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
