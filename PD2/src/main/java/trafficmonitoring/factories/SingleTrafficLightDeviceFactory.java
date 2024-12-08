package main.java.trafficmonitoring.factories;


import main.java.trafficmonitoring.devices.PedestrianPresenceSensor;
import main.java.trafficmonitoring.devices.TrafficLightSensorActuator;
import main.java.trafficmonitoring.devices.VehiclePresenceSensor;
import main.java.trafficmonitoring.logging.TrafficLogger;

public class SingleTrafficLightDeviceFactory implements DeviceFactory {

    @Override
    public PedestrianPresenceSensor createPedestrianSensor() {
        PedestrianPresenceSensor sensor = new PedestrianPresenceSensor("Pedestrian Sensor");
        // Perform additional tasks if needed
        TrafficLogger.info("[Console] CREATION OF :" + sensor.getName());
        System.out.println("Created " + sensor.getName());
        return sensor;
    }

    @Override
    public VehiclePresenceSensor createVehicleSensor(String lane) {
        VehiclePresenceSensor sensor = new VehiclePresenceSensor("Vehicle Sensor " + lane);
        // Perform additional tasks if needed
        TrafficLogger.info("[Console] CREATION OF :" + sensor.getName());
        System.out.println("Created " + sensor.getName());
        return sensor;
    }

    @Override
    public TrafficLightSensorActuator createTrafficLight(String lane) {
        TrafficLightSensorActuator actuator = new TrafficLightSensorActuator("Traffic Light " + lane);
        // Perform additional tasks if needed
        TrafficLogger.info("[Console] CREATION OF :" + actuator.getName());
        System.out.println("Created " + actuator.getName());
        return actuator;
    }
}