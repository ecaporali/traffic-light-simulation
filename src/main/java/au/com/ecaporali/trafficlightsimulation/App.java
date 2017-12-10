package au.com.ecaporali.trafficlightsimulation;

import au.com.ecaporali.trafficlightsimulation.config.SimulationConfig;
import au.com.ecaporali.trafficlightsimulation.controller.TrafficLightsController;

/**
 * @author Enrico Caporali
 */
public class App {

    private static final int TIME_TO_RUN = 30 * 60;     // 1800s

    public static void main(String[] args) {
        SimulationConfig simulationConfig = new SimulationConfig();
        TrafficLightsController controller = new TrafficLightsController(simulationConfig);
        controller.simulate(TIME_TO_RUN);
        controller.showLogs();
    }
}
