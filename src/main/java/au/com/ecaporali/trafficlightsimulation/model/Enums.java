package au.com.ecaporali.trafficlightsimulation.model;

/**
 * @author Enrico Caporali
 */
public final class Enums {

    public enum LightType {

        GREEN("Green", 60 * 5 - 30),        // 270s
        YELLOW("Yellow", 60 / 2),           // 30s
        RED("Red", 60 * 5);                 // 300s

        private String printName;
        private int duration;

        LightType(String printName, int duration) {
            this.printName = printName;
            this.duration = duration;
        }

        public int duration() {
            return this.duration;
        }

        public String printName() {
            return this.printName;
        }
    }

    public enum Location {

        NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

        private String printName;

        Location(String printName) {
            this.printName = printName;
        }

        @Override
        public String toString() {
            return this.printName;
        }
    }
}
