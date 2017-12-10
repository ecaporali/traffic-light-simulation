package au.com.ecaporali.trafficlightsimulation.config;

import au.com.ecaporali.trafficlightsimulation.controller.TrafficLightsObservable;
import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import au.com.ecaporali.trafficlightsimulation.model.Enums.Location;
import au.com.ecaporali.trafficlightsimulation.model.TrafficLights;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Enrico Caporali
 */
public class SimulationConfig {

    private TrafficLightsObservable observable;

    public SimulationConfig() {
        List<TrafficLights> trafficLights = new ArrayList<>(2);
        trafficLights.add(new TrafficLights(LightType.RED, Location.NORTH, Location.SOUTH));
        trafficLights.add(new TrafficLights(LightType.YELLOW, Location.EAST, Location.WEST));
        this.observable = new TrafficLightsObservable(trafficLights);
    }

    public SimulationConfig(List<TrafficLights> trafficLights) {
        this.observable = new TrafficLightsObservable(trafficLights);
    }

    public TrafficLightsObservable observableConfig() {
        return observable;
    }
}
