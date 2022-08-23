package duke.task;

/**
 * Encapsulates a to-do task.
 */
public class TodoTask extends Task {
    /**
     * Constructs a new {@code TodoTask} with given description.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(TaskSymbolType.T, description);
    }
}
