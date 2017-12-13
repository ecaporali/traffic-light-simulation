package au.com.ecaporali.trafficlightsimulation.controller;

import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import au.com.ecaporali.trafficlightsimulation.model.TrafficLights;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Enrico Caporali
 */
@RunWith(MockitoJUnitRunner.class)
public class TrafficLightsObservableTest {

    private TrafficLightsObservable observable;

    @Mock
    private TrafficLights trafficLightsA;

    @Mock
    private TrafficLights trafficLightsB;

    @Before
    public void setUp() throws Exception {
        List<TrafficLights> trafficLights = Arrays.asList(trafficLightsA, trafficLightsB);
        observable = new TrafficLightsObservable(trafficLights);
    }

    @Test
    public void shouldUpdateObserversWhenNotifyAllObserversIsCalled() {
        LightType[] lightTypes = {LightType.GREEN};
        observable.notifyAllObservers(lightTypes);
        verify(trafficLightsA, times(1)).update(observable, lightTypes);
        verify(trafficLightsB, times(1)).update(observable, lightTypes);
    }

    @Test
    public void shouldShowAllLogsWhenBothOutputSizesAreEqual() {
        String[] outputA = new String[]{"(A, B): Green", "(A, B): Red"};
        String[] outputB = new String[]{"(E, W): Red", "(E, W): green"};
        when(trafficLightsA.colorAtLocations()).thenReturn(Arrays.asList(outputA));
        when(trafficLightsB.colorAtLocations()).thenReturn(Arrays.asList(outputB));

        String expectedOutput = observable.buildLogs();

        assertThat(expectedOutput, equalTo(outputA[0] + " - " + outputB[0] + "\n" + outputA[1] + " - " + outputB[1]));
    }

    @Test
    public void shouldNotShowAllLogsWhenOutputSizesAreDifferent() {
        String[] outputA = new String[]{"(A, B): Green", "(A, B): Red"};
        String[] outputB = new String[]{"(E, W): Red"};
        when(trafficLightsA.colorAtLocations()).thenReturn(Arrays.asList(outputA));
        when(trafficLightsB.colorAtLocations()).thenReturn(Arrays.asList(outputB));

        String expectedOutput = observable.buildLogs();

        assertFalse(expectedOutput.contains(outputA[1]));
        assertThat(expectedOutput, equalTo(outputA[0] + " - " + outputB[0]));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenTrafficLightsListIsNull() {
        new TrafficLightsObservable(null);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenTrafficLightsListSizeIsLessThanTwoElements() {
        List<TrafficLights> trafficLights = Collections.singletonList(trafficLightsA);
        new TrafficLightsObservable(trafficLights);
    }
}
