package task;

/**
 * An enum used to distinguish between different task types.
 */
public enum TaskType {
    T("todo"),      // Todo
    D("deadline"),  // Deadline
    E("event");     // Event

    private final String value;

    TaskType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
