package duke.tasks;

/**
 * Represents a task without any date attached to it.
 */
public class Todo extends Task {
    private static final String TASK_SYMBOL = "T";

    /**
     * Constructor for a Todo.
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the Todo task.
     * @return a String indicating the Todo task's symbol, status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", Todo.TASK_SYMBOL, super.toString());
    }

    /**
     * Returns the type of the Todo task.
     * @return "T".
     */
    @Override
    public String getType() {
        return Todo.TASK_SYMBOL;
    }

    /**
     * Returns the date of the Todo task.
     * @return an empty String.
     */
    @Override
    public String getDate() {
        return " ";
    }

    /**
     * Checks if the Todo task occurs on a specific date.
     * @param date The date to check if the Todo task occurs on.
     * @return False.
     */
    @Override
    public boolean isOnDate(String date) {
        return false;
    }
}
