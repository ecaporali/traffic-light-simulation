package au.com.ecaporali.trafficlightsimulation.controller;

import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import au.com.ecaporali.trafficlightsimulation.model.TrafficLights;

import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static au.com.ecaporali.trafficlightsimulation.utils.AssertUtils.checkCondition;
import static au.com.ecaporali.trafficlightsimulation.utils.AssertUtils.checkNonNull;

/**
 * @author Enrico Caporali
 */
public class TrafficLightsObservable extends Observable {

    private List<TrafficLights> trafficLights;

    public TrafficLightsObservable(List<TrafficLights> trafficLights) {
        checkNonNull(trafficLights, "trafficLights cannot be null");
        checkCondition(trafficLights.size() < 2, "trafficLights must contain at least 2 elements");

        this.trafficLights = trafficLights;
        this.trafficLights.forEach(this::addObserver);
    }

    public void notifyAllObservers(LightType... types) {
        super.setChanged();
        super.notifyObservers(types);
    }

    public String buildLogs() {
        List<String> outputNS = trafficLights.get(0).colorAtLocations();
        List<String> outputEW = trafficLights.get(1).colorAtLocations();

        return IntStream.range(0, Math.min(outputNS.size(), outputEW.size()))
                .mapToObj(i -> outputNS.get(i) + " - " + outputEW.get(i))
                .collect(Collectors.joining("\n"));
    }
}
