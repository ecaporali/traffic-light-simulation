package au.com.ecaporali.trafficlightsimulation.utils;

/**
 * @author Enrico Caporali
 */
public class AssertUtils {

    public static void checkNonNull(Object object, String message) {
        if (object == null) {
            throw new AssertionError(message);
        }
    }

    public static void checkCondition(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }
}
