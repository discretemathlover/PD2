package main.java.trafficmonitoring.factories;

import main.java.trafficmonitoring.devices.PedestrianPresenceSensor;
import main.java.trafficmonitoring.devices.TrafficLightSensorActuator;
import main.java.trafficmonitoring.devices.VehiclePresenceSensor;

public interface DeviceFactory {
    PedestrianPresenceSensor createPedestrianSensor();
    VehiclePresenceSensor createVehicleSensor(String lane);
    TrafficLightSensorActuator createTrafficLight(String lane);
}