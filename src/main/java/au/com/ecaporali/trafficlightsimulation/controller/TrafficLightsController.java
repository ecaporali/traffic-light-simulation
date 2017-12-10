package au.com.ecaporali.trafficlightsimulation.controller;

import au.com.ecaporali.trafficlightsimulation.config.SimulationConfig;
import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;

/**
 * @author Enrico Caporali
 */
public class TrafficLightsController {

    private TrafficLightsObservable observable;

    public TrafficLightsController(SimulationConfig config) {
        this.observable = config.observableConfig();
    }

    public void simulate(int timeToRun) {
        int currentTime = 0;
        while (currentTime < timeToRun) {
            if (currentTime % LightType.RED.duration() == 0) {
                updateTrafficLights(LightType.YELLOW, LightType.RED);
            } else if (currentTime % LightType.GREEN.duration() == 0) {
                updateTrafficLights(LightType.GREEN);
            }
            currentTime += LightType.YELLOW.duration();
        }
    }

    public void showLogs() {
        System.out.println(observable.buildLogs());
    }

    private void updateTrafficLights(LightType... types) {
        observable.notifyAllObservers(types);
    }
}
