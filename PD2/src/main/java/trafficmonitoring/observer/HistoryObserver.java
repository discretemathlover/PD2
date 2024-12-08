package main.java.trafficmonitoring.observer;

import main.java.trafficmonitoring.logging.TrafficLogger;

import java.util.ArrayList;
import java.util.List;

public class HistoryObserver implements Observer {
    private final List<String> history = new ArrayList<>();

    @Override
    public void update(String event) {
        history.add(event);
        TrafficLogger.info("[History] Event recorded: " + event);
    }

    public List<String> getHistory() {
        return history;
    }
}