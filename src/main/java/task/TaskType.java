package task;

/**
 * Enum to record different task types
 */
public enum TaskType {
    TODO("TODO"),
    EVENT("EVENT"),
    DEADLINE("DEADLINE");

    public final String type;

    private TaskType(String type) {
        this.type = type;
    }
}
