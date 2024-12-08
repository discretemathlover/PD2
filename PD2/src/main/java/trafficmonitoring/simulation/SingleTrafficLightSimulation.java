package main.java.trafficmonitoring.simulation;

import main.java.trafficmonitoring.enums.LightStatus;
import main.java.trafficmonitoring.devices.PedestrianPresenceSensor;
import main.java.trafficmonitoring.devices.TrafficLightSensorActuator;
import main.java.trafficmonitoring.devices.VehiclePresenceSensor;
import main.java.trafficmonitoring.factories.DeviceFactory;

public class SingleTrafficLightSimulation implements SimulationStrategy {
    static final int VEHICLE_TRAFFIC = 0;
    static final int PEDESTRIAN_TRAFFIC = 1;
    static final int VEHICLE_WAITING = 2;
    static final int PEDESTRIAN_WAITING = 3;

    private final DeviceFactory factory;

    private final PedestrianPresenceSensor pedestrianSensor;
    private final VehiclePresenceSensor vehicleSensorLeft;
    private final VehiclePresenceSensor vehicleSensorRight;
    private final TrafficLightSensorActuator trafficLightLeft;
    private final TrafficLightSensorActuator trafficLightRight;
    private final TrafficLightSensorActuator pedestrianLight1;
    private final TrafficLightSensorActuator pedestrianLight2;

    private int state = VEHICLE_TRAFFIC;
    private final String[] simulationStateNames = {
            "Vehicle Traffic", "Pedestrian Traffic", "Vehicle Waiting", "Pedestrian Waiting"
    };

    public SingleTrafficLightSimulation(DeviceFactory factory) {
        this.factory = factory;

        // Create main.java.trafficmonitoring.devices using the factory
        this.pedestrianSensor = factory.createPedestrianSensor();
        this.vehicleSensorLeft = factory.createVehicleSensor("Vehicle Sensor Left Lane");
        this.vehicleSensorRight = factory.createVehicleSensor("Vehicle Sensor Right Lane");
        this.trafficLightLeft = factory.createTrafficLight("Traffic Light Left Lane");
        this.trafficLightRight = factory.createTrafficLight("Traffic Light Right Lane");
        this.pedestrianLight1 = factory.createTrafficLight("Pedestrian Traffic Light 1");
        this.pedestrianLight2 = factory.createTrafficLight("Pedestrian Traffic Light 2");
    }

    private void switchToVehicleTraffic(TrafficSimulation simulation) {
        pedestrianLight1.setLightStatus(LightStatus.RED);
        pedestrianLight2.setLightStatus(LightStatus.RED);

        trafficLightLeft.setLightStatus(LightStatus.ORANGE);
        trafficLightRight.setLightStatus(LightStatus.ORANGE);

        trafficLightLeft.setLightStatus(LightStatus.GREEN);
        trafficLightRight.setLightStatus(LightStatus.GREEN);

        simulation.notifyObservers("Switched to Vehicle Traffic");
    }

    private void switchToPedestrianTraffic(TrafficSimulation simulation) {
        trafficLightLeft.setLightStatus(LightStatus.ORANGE);
        trafficLightRight.setLightStatus(LightStatus.ORANGE);

        trafficLightLeft.setLightStatus(LightStatus.RED);
        trafficLightRight.setLightStatus(LightStatus.RED);

        pedestrianLight1.setLightStatus(LightStatus.GREEN);
        pedestrianLight2.setLightStatus(LightStatus.GREEN);

        simulation.notifyObservers("Switched to Pedestrian Traffic");
    }

    @Override
    public void simulate(int steps, TrafficSimulation simulation) {
        for (int i = 0; i < steps; i++) {
            simulation.notifyObservers(STR."STEP: \{i} (State: \{simulationStateNames[state]})");

            switch (state) {
                case VEHICLE_TRAFFIC -> {
                    if (i % 3 == 0) {
                        pedestrianSensor.setPedestrianPresent(true);
                        simulation.notifyObservers("Pedestrian detected by sensor.");
                        state = PEDESTRIAN_WAITING;
                    }
                }
                case PEDESTRIAN_WAITING -> {
                    switchToPedestrianTraffic(simulation);
                    pedestrianSensor.setPedestrianPresent(false);
                    state = PEDESTRIAN_TRAFFIC;
                }
                case PEDESTRIAN_TRAFFIC -> {
                    if (i % 3 != 0) {
                        if (i % 2 == 0) {
                            vehicleSensorLeft.setVehiclePresent(true);
                            simulation.notifyObservers("Vehicle detected in left lane.");
                        } else {
                            vehicleSensorRight.setVehiclePresent(true);
                            simulation.notifyObservers("Vehicle detected in right lane.");
                        }
                    }
                    state = VEHICLE_WAITING;
                }
                case VEHICLE_WAITING -> {
                    switchToVehicleTraffic(simulation);
                    vehicleSensorRight.setVehiclePresent(false);
                    vehicleSensorLeft.setVehiclePresent(false);
                    simulation.notifyObservers("No vehicles waiting in lanes.");
                    state = VEHICLE_TRAFFIC;
                }
            }
        }
    }
}
