package au.com.ecaporali.trafficlightsimulation.model;

import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import au.com.ecaporali.trafficlightsimulation.model.Enums.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static au.com.ecaporali.trafficlightsimulation.utils.AssertUtils.checkNonNull;

/**
 * @author Enrico Caporali
 */
public class TrafficLight {

    private LightType currentLightType;
    private List<LightType> lights;
    private Location location;
    private Logbook logbook;

    public TrafficLight(LightType defaultLightType, Location location, Logbook logbook) {
        checkNonNull(defaultLightType, "defaultLightType cannot be null");
        checkNonNull(location, "location cannot be null");
        checkNonNull(logbook, "logbook cannot be null");

        this.currentLightType = defaultLightType;
        this.location = location;
        this.logbook = logbook;
        this.initLights();
    }

    private void initLights() {
        LightType[] values = LightType.values();
        this.lights = new ArrayList<>(values.length);
        this.lights.addAll(Arrays.asList(values));
    }

    public void updateCurrentLight(LightType... lightTypes) {
        Arrays.stream(lightTypes)
                .filter(type -> this.currentLightType.equals(type))
                .findFirst().ifPresent(this::nextLight);

        this.logbook.add(this.currentLightType);
    }

    private void nextLight(LightType lightType) {
        int position = (lightType.ordinal() + 1) % 3;
        this.currentLightType = lights.get(position);
    }

    LightType getCurrentLightType() {
        return currentLightType;
    }
}
