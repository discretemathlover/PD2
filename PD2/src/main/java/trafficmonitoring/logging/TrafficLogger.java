package main.java.trafficmonitoring.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TrafficLogger {
    private static final Logger logger = Logger.getLogger(TrafficLogger.class.getName());

    static {
        try {
            String logFileName = System.getProperty("logFile", "simulation.log");
            FileHandler fileHandler = new FileHandler(logFileName, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
            logger.setUseParentHandlers(true); // Fall back to console logging
        }
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warning(message);
    }

    public static void error(String message) {
        logger.severe(message);
    }
}
