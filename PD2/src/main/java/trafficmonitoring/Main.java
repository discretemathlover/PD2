package main.java.trafficmonitoring;

import main.java.trafficmonitoring.factories.SingleTrafficLightDeviceFactory;
import main.java.trafficmonitoring.logging.TrafficLogger;
import main.java.trafficmonitoring.observer.ConsoleOutputObserver;
import main.java.trafficmonitoring.observer.HistoryObserver;
import main.java.trafficmonitoring.simulation.SingleTrafficLightSimulation;
import main.java.trafficmonitoring.simulation.TrafficSimulation;

public class Main {
    public static void main(String[] args) {
        // Initialize the device factory
        SingleTrafficLightDeviceFactory deviceFactory = new SingleTrafficLightDeviceFactory();

        // Create the simulation with the factory
        SingleTrafficLightSimulation simulation = new SingleTrafficLightSimulation(deviceFactory);

        // Observers
        ConsoleOutputObserver consoleObserver = new ConsoleOutputObserver();
        HistoryObserver historyObserver = new HistoryObserver();

        // Wrap the simulation with TrafficSimulation (Observer-enabled subject)
        TrafficSimulation trafficSimulation;
        trafficSimulation = new TrafficSimulation(simulation);

        // Add observers
        trafficSimulation.addObserver(consoleObserver);
        trafficSimulation.addObserver(historyObserver);

        // Simulate traffic control
        trafficSimulation.simulate(10);

        // Print simulation history
        TrafficLogger.info("Simulation History: " + historyObserver.getHistory());
    }
}
