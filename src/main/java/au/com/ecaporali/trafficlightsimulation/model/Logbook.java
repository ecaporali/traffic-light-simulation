package au.com.ecaporali.trafficlightsimulation.model;

import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import au.com.ecaporali.trafficlightsimulation.model.Enums.Location;
import au.com.ecaporali.trafficlightsimulation.utils.AssertUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Enrico Caporali
 */
public class Logbook {

    private Location location;
    private List<LightType> lights;

    private Logbook() {
        this.lights = new ArrayList<>();
    }

    public Logbook(Location location) {
        this();
        AssertUtils.checkNonNull(location, "location cannot be null");
        this.location = location;
    }

    public void add(LightType lightType) {
        lights.add(lightType);
    }

    public List<LightType> revealContent() {
        return Collections.unmodifiableList(lights);
    }

    public String locationToDisplay() {
        return location.toString();
    }
}
