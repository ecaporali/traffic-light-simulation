package au.com.ecaporali.trafficlightsimulation.config;

import au.com.ecaporali.trafficlightsimulation.controller.TrafficLightsObservable;
import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import au.com.ecaporali.trafficlightsimulation.model.Enums.Location;
import au.com.ecaporali.trafficlightsimulation.model.TrafficLights;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Enrico Caporali
 */
@RunWith(MockitoJUnitRunner.class)
public class SimulationConfigTest {

    private SimulationConfig simulationConfig;

    @Test
    public void shouldAddDefaultNumbersOfObserversOnInit() throws Exception {
        simulationConfig = new SimulationConfig();
        TrafficLightsObservable observable = simulationConfig.observableConfig();
        assertThat(observable, notNullValue());
        assertThat(observable.countObservers(), is(2));
    }

    @Test
    public void shouldAddCustomNumbersOfObserversOnInit() throws Exception {
        List<TrafficLights> trafficLights = new ArrayList<>();
        trafficLights.add(new TrafficLights(LightType.GREEN, Location.NORTH, Location.WEST));
        trafficLights.add(new TrafficLights(LightType.YELLOW, Location.WEST, Location.EAST));
        trafficLights.add(new TrafficLights(LightType.RED, Location.EAST, Location.SOUTH));

        simulationConfig = new SimulationConfig(trafficLights);

        assertThat(simulationConfig.observableConfig().countObservers(), is(3));
    }
}
