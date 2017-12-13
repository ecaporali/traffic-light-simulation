package au.com.ecaporali.trafficlightsimulation.model;

import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import au.com.ecaporali.trafficlightsimulation.model.Enums.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Enrico Caporali
 */
@RunWith(MockitoJUnitRunner.class)
public class TrafficLightTest {

    private TrafficLight trafficLight;

    @Mock
    private Logbook logbook;

    @Test
    public void shouldUpdateCurrentLightToYellowWhenCurrentLightTypeIsGreen() {
        updateCurrentLightFromTo(LightType.GREEN, LightType.YELLOW);
    }

    @Test
    public void shouldUpdateCurrentLightToRedWhenCurrentLightTypeIsYellow() {
        updateCurrentLightFromTo(LightType.YELLOW, LightType.RED);
    }

    @Test
    public void shouldUpdateCurrentLightToGreenWhenCurrentLightTypeIsRed() {
        updateCurrentLightFromTo(LightType.RED, LightType.GREEN);
    }

    @Test
    public void shouldNotUpdateCurrentLightWhenCurrentLightTypeIsDifferent() {
        trafficLight = new TrafficLight(LightType.RED, Location.NORTH, logbook);
        LightType[] lightTypes = {LightType.GREEN};

        trafficLight.updateCurrentLight(lightTypes);

        assertThat(trafficLight.getCurrentLightType(), equalTo(LightType.RED));
        verify(logbook, times(1)).add(LightType.RED);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenDefaultLightTypeIsNull() {
        new TrafficLight(null, Location.NORTH, logbook);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenLocationIsNull() {
        new TrafficLight(LightType.GREEN, null, logbook);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenLogbookIsNull() {
        new TrafficLight(LightType.GREEN, Location.NORTH, null);
    }

    private void updateCurrentLightFromTo(LightType currentLightType, LightType nextLightType) {
        trafficLight = new TrafficLight(currentLightType, Location.NORTH, logbook);
        LightType[] lightTypes = {currentLightType};

        trafficLight.updateCurrentLight(lightTypes);

        assertThat(trafficLight.getCurrentLightType(), equalTo(nextLightType));
        verify(logbook).add(nextLightType);
    }
}
