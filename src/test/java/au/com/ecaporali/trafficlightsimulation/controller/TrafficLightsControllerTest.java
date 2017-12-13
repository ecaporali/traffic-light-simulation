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
    public void setUp() {
        when(config.observableConfig()).thenReturn(observable);
        trafficLightsController = new TrafficLightsController(config);
    }

    @Test
    public void shouldCallUpdateOnlyForGreenLightOnce() {
        int timeToRun = 300;
        trafficLightsController.simulate(timeToRun);
        verify(observable, times(1)).notifyAllObservers(eq(LightType.GREEN));
    }

    @Test
    public void shouldCallUpdateForAllLightsOnce() {
        int timeToRun = 300;
        trafficLightsController.simulate(timeToRun);
        verify(observable, times(1)).notifyAllObservers(eq(LightType.GREEN));
        verify(observable, times(1)).notifyAllObservers(eq(LightType.YELLOW), eq(LightType.RED));
    }

    @Test
    public void shouldCallUpdateForAllLightsSixTimes() {
        int timeToRun = 1800;
        trafficLightsController.simulate(timeToRun);
        verify(observable, times(6)).notifyAllObservers(eq(LightType.GREEN));
        verify(observable, times(6)).notifyAllObservers(eq(LightType.YELLOW), eq(LightType.RED));
    }

    @Test
    public void shouldNotCallUpdateWhenTimeToRunIsZero() {
        int timeToRun = 0;
        trafficLightsController.simulate(timeToRun);
        verify(observable, times(0)).notifyAllObservers(any());
    }
}
