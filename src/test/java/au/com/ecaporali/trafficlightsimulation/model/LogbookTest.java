package au.com.ecaporali.trafficlightsimulation.model;

import au.com.ecaporali.trafficlightsimulation.model.Enums.LightType;
import au.com.ecaporali.trafficlightsimulation.model.Enums.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Enrico Caporali
 */
@RunWith(MockitoJUnitRunner.class)
public class LogbookTest {

    private Logbook logbook;

    @Before
    public void setUp() {
        logbook = new Logbook(Location.NORTH);
    }

    @Test
    public void shouldReturnLocationPrintedName() {
        assertThat(logbook.locationToDisplay(), equalTo("N"));
    }

    @Test
    public void shouldAddLights() {
        logbook.add(LightType.GREEN);
        logbook.add(LightType.RED);
        assertThat(logbook.revealContent().size(), is(2));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowExceptionWhenLocationIsNull() {
        logbook = new Logbook(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenRevealContentListIsModified() {
        logbook.add(LightType.GREEN);
        logbook.add(LightType.RED);
        List<LightType> contents = logbook.revealContent();
        contents.remove(LightType.RED);
    }
}
