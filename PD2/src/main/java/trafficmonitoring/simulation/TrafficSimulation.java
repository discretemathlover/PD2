package main.java.trafficmonitoring.simulation;

import main.java.trafficmonitoring.observer.Observer;
import main.java.trafficmonitoring.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class TrafficSimulation implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private SimulationStrategy strategy;

    public TrafficSimulation(SimulationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SimulationStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }
        this.strategy = strategy;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
    public void simulate(int steps) {
        if (strategy == null) {
            throw new IllegalStateException("No simulation strategy defined.");
        }
        strategy.simulate(steps, this);
    }


}