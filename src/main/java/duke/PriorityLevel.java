package duke;

/**
 * Encapsulates a PriorityLevel.
 */
public class PriorityLevel {

    /**
     * Creates a Priority enum.
     */
    public enum Priority {
        URGENT,
        HIGH,
        MEDIUM,
        LOW
    }

    /**
     * Returns the Enum representation of the task is stored in a file.
     * @return Enum of the task.
     */
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
