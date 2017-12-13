package au.com.ecaporali.trafficlightsimulation.model;

import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Enrico Caporali
 */
@RunWith(MockitoJUnitRunner.class)
public class TrafficLightsTest {

    private TrafficLights trafficLights;

    @Mock
    private TrafficLight trafficLight;

    @Mock
    private Logbook logbook;

    @Before
    public void setUp() {
        trafficLights = new TrafficLights(singletonList(trafficLight), singletonList(logbook));
    }

    @Test
    public void shouldCallUpdateMethodForAllTrafficLights() {
        LightType[] types = {LightType.GREEN};
        trafficLights.update(null, types);
        verify(trafficLight).updateCurrentLight(types);
    }

    @Test
    public void shouldReturnListOfWellFormattedStringsWhenColorAtLocationsIsCalled() {
        when(logbook.revealContent()).thenReturn(Arrays.asList(LightType.GREEN, LightType.YELLOW, LightType.RED));
        when(logbook.locationToDisplay()).thenReturn("N");

        List<String> actual = trafficLights.colorAtLocations();
        List<String> expected = Arrays.asList("(N): Green", "(N): Yellow", "(N): Red");

        Assert.assertThat(actual, equalTo(expected));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenLocationIsNullOnInit() {
        new TrafficLights(LightType.GREEN);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenLocationIsEmptyOnInit() {
        new TrafficLights(LightType.GREEN);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenTrafficLightsIsNullOnInit() {
        new TrafficLights(null, singletonList(logbook));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenLogbooksIsNullOnInit() {
        new TrafficLights(singletonList(trafficLight), null);
    }
}
