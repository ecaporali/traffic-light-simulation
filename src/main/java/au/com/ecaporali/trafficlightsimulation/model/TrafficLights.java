package au.com.ecaporali.trafficlightsimulation.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import static au.com.ecaporali.trafficlightsimulation.model.Enums.Location;
import static au.com.ecaporali.trafficlightsimulation.utils.AssertUtils.checkCondition;
import static au.com.ecaporali.trafficlightsimulation.utils.AssertUtils.checkNonNull;

/**
 * @author Enrico Caporali
 */
public class TrafficLights implements Observer {

    private List<TrafficLight> trafficLights;
    private List<Logbook> logbooks;

    public TrafficLights(LightType lightType, Location... locations) {
        checkNonNull(locations, "locations cannot be null");
        checkCondition(locations.length == 0, "trafficLights must have at least one location");

        this.trafficLights = new ArrayList<>(locations.length);
        this.logbooks = new ArrayList<>(locations.length);
        Arrays.stream(locations).forEach(location -> initTrafficLights(lightType, location));
    }

    TrafficLights(List<TrafficLight> trafficLights, List<Logbook> logbooks) {
        checkNonNull(trafficLights, "trafficLights cannot be null");
        checkNonNull(logbooks, "logbooks cannot be null");

        this.trafficLights = trafficLights;
        this.logbooks = logbooks;
    }

    private void initTrafficLights(LightType lightType, Location location) {
        Logbook logbook = new Logbook(location);
        this.logbooks.add(logbook);
        this.trafficLights.add(new TrafficLight(lightType, location, logbook));
    }

    @Override
    public void update(Observable observable, Object types) {
        this.trafficLights.forEach(it -> it.updateCurrentLight((LightType[]) types));
    }

    public List<String> colorAtLocations() {
        return this.logbooks.stream()
                .findFirst()
                .map(logbook -> formatLogbook(logbook).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private Stream<String> formatLogbook(Logbook logbook) {
        return logbook.revealContent().stream()
                .map(content -> "(" + locations() + "): " + content.printName());
    }

    private String locations() {
        return this.logbooks.stream()
                .map(Logbook::locationToDisplay)
                .collect(Collectors.joining(", "));
    }
}
