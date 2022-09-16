package duke;


public class PriorityLevel {

    public enum Priority {
        URGENT,
        HIGH,
        MEDIUM,
        LOW
    }


    public static Priority getPriorityString(String level) {
        switch (level.toUpperCase()) {
        case "URGENT":
            return Priority.URGENT;
        case "HIGH":
            return Priority.HIGH;
        case "MEDIUM":
            return Priority.MEDIUM;
        default:
            return Priority.LOW;
        }
    }
}