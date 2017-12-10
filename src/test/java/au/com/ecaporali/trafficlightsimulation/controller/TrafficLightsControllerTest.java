package au.com.ecaporali.trafficlightsimulation.controller;

import au.com.ecaporali.trafficlightsimulation.config.SimulationConfig;
import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Enrico Caporali
 */
@RunWith(MockitoJUnitRunner.class)
public class TrafficLightsControllerTest {

    private TrafficLightsController trafficLightsController;

    @Mock
    private TrafficLightsObservable observable;

    @Mock
    private SimulationConfig config;

    @Before
    public void setUp() throws Exception {
        when(config.observableConfig()).thenReturn(observable);
        trafficLightsController = new TrafficLightsController(config);
    }

    @Test
    public void shouldCallUpdateOnlyForGreenLightOnce() throws Exception {
        int timeToRun = 300;
        trafficLightsController.simulate(timeToRun);
        LightType[] green = {LightType.GREEN};
        verify(observable, times(1)).notifyAllObservers(eq(green));
    }

    @Test
    public void shouldCallUpdateForAllLightsOnce() throws Exception {
        int timeToRun = 300;
        trafficLightsController.simulate(timeToRun);
        LightType[] green = {LightType.GREEN};
        LightType[] yellowAndRed = {LightType.YELLOW, LightType.RED};
        verify(observable, times(1)).notifyAllObservers(eq(green));
        verify(observable, times(1)).notifyAllObservers(eq(yellowAndRed));
    }

    @Test
    public void shouldCallUpdateForAllLightsSixTimes() throws Exception {
        int timeToRun = 1800;
        trafficLightsController.simulate(timeToRun);
        LightType[] green = {LightType.GREEN};
        LightType[] yellowAndRed = {LightType.YELLOW, LightType.RED};
        verify(observable, times(6)).notifyAllObservers(eq(green));
        verify(observable, times(6)).notifyAllObservers(eq(yellowAndRed));
    }

    @Test
    public void shouldNotCallUpdateWhenTimeToRunIsZero() throws Exception {
        int timeToRun = 0;
        trafficLightsController.simulate(timeToRun);
        verify(observable, times(0)).notifyAllObservers(any());
    }
}
