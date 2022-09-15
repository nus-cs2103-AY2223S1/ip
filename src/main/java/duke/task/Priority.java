package duke.task;

/**
 * Priority level for a task.
 */
public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low"),
    NONE("none");

    protected static final String UNKNOWN_PRIORITY = "Unknown priority, please input high/medium/low/none!";
    /* The input priority by the user. */
    private final String priority;

    /**
     * Constructor for the Priority enum.
     *
     * @param priority Priority level specified.
     */
    Priority(String priority) {
        this.priority = priority;
    }

    /**
     * Returns the priority if a correct priority is specified.
     *
     * @param priority Input priority by user.
     * @return Enum value corresponding to the input priority.
     * @throws IllegalArgumentException if invalid priority is given.
     */
    public static Priority getPriority(String priority) throws IllegalArgumentException {
        for (Priority p : Priority.values()) {
            if (priority.equals(p.priority)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Priority Level must be low, medium, high or none!");
    }

    /**
     * Overrides toString method for the priority enum class.
     *
     * @return The priority level in a readable string.
     */
    @Override
    public String toString() {
        return "Priority: " + priority.toUpperCase();
    }

    /**
     * Adjusts the priority to be in a parse-friendly format for the storage class.
     *
     * @return Priority in a string format.
     */
    public String saveFormat() {
        return priority;
    }
}
