package main.java.trafficmonitoring.observer;

import main.java.trafficmonitoring.logging.TrafficLogger;

public class ConsoleOutputObserver implements Observer {
    @Override
    public void update(String event) {
        TrafficLogger.info("[Console] " + event);
    }
}
