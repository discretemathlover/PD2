package test.java.trafficmonitoring;

import org.junit.Test;
import main.java.trafficmonitoring.observer.HistoryObserver;
import main.java.trafficmonitoring.simulation.TrafficSimulation;
import main.java.trafficmonitoring.factories.SingleTrafficLightDeviceFactory;
import main.java.trafficmonitoring.simulation.SingleTrafficLightSimulation;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SingleTrafficLightSimulationTest {

    @Test
    public void testDeviceCreationAndSimulation() {
        // Use factory for device creation
        SingleTrafficLightDeviceFactory factory = new SingleTrafficLightDeviceFactory();

        // Initialize simulation
        TrafficSimulation simulation = new TrafficSimulation(new SingleTrafficLightSimulation(factory));
        HistoryObserver historyObserver = new HistoryObserver();
        simulation.addObserver(historyObserver);

        // Run simulation
        simulation.simulate(3);

        // Verify observer history
        List<String> history = historyObserver.getHistory();
        assertTrue(history.contains("STEP: 0"));
        assertTrue(history.stream().anyMatch(event -> event.contains("Pedestrian Sensor Status")));
        assertTrue(history.stream().anyMatch(event -> event.contains("Vehicle Sensors:")));
    }
}
