package main.java.trafficmonitoring.devices;

import main.java.trafficmonitoring.logging.TrafficLogger;

public class PedestrianPresenceSensor extends Device {
    private boolean pedestrianPresent = false;

    public PedestrianPresenceSensor(String name) {
        super(name);
    }

    public void setPedestrianPresent(boolean presence) {
        pedestrianPresent = presence;
        TrafficLogger.info("Pedestrian presence status: " + presence + " (Device: " + getName() + ")");

    }

    public boolean isPedestrianPresent() {
        return pedestrianPresent;
    }
}
