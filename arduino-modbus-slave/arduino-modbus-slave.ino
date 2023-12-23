#include <SoftwareSerial.h>
#include <Wire.h>
#include <SPI.h>
#include <Adafruit_BMP280.h>

// https://github.com/smarmengol/Modbus-Master-Slave-for-Arduino/blob/master/ModbusRtu.h
#include "ModbusRtu.h"

Adafruit_BMP280 bmp;
Adafruit_Sensor *bmp_temp = bmp.getTemperatureSensor();
Adafruit_Sensor *bmp_pressure = bmp.getPressureSensor();

#define ID 1  // Modbus slave address

Modbus slave(ID, Serial, 0);

int8_t state = 0;  // Request state

uint16_t data[6];  // Data array



void setup() {

  iosetup();

  BMPSetup();

  slave.begin(115200);  // Modbus setup on 115200 baud rate
}



void loop() {

  sensors_event_t temp_event, pressure_event;
  bmp_temp->getEvent(&temp_event);
  bmp_pressure->getEvent(&pressure_event);

  data[4] = temp_event.temperature * 100;
  data[5] = pressure_event.pressure * 10;

  state = slave.poll(data, 6);  // Polling data

  if (state > 4) {
    for(int i = 2; i <= 13; i++) {
      digitalWrite(i, bitRead(data[0], i - 2));  // writing data on pins
    }
  }

  delay(10);
}



void iosetup() {
  for(int i = 2; i <= 13; i++) {
      pinMode(i, OUTPUT);
  }
}

void BMPSetup() {
  bmp.begin(BMP280_ADDRESS_ALT, BMP280_CHIPID);
  bmp.setSampling(Adafruit_BMP280::MODE_NORMAL,
                  Adafruit_BMP280::SAMPLING_X2,
                  Adafruit_BMP280::SAMPLING_X16,
                  Adafruit_BMP280::FILTER_X16,
                  Adafruit_BMP280::STANDBY_MS_500);
}