package task;

/**
 * An enum used to distinguish between different task types.
 */
public enum TaskType {
    T("todo"),
    D("deadline"),
    E("event");

    /**
     * Value is used for printing the full name of the task type.
     */
    private final String value;

    /**
     * Constructs a task type enum, with a given value.
     *
     * @param value The specified value.
     */
    TaskType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
