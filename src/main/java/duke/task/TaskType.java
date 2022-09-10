package duke.task;

/**
 * Represents the type of tasks
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    /**
     * Returns a task symbol for each type of task.
     *
     * @return The task symbol for each type of task.
     */
    public String getTaskSymbol() {
        switch (this) {
        case TODO: return "T";
        case DEADLINE: return "D";
        case EVENT: return "E";
        default:
            return null;
        }
    }
}
