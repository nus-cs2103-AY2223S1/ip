package duke;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {
    private String type;

    /**
     * Creates a todo task.
     * @param name Name of the task
     * @param isDone Status of whether the task is marked or done.
     */
    ToDo(String name, boolean isDone) {
        super(name, isDone);
        this.type = "[T]";
    }

    /**
     * Represents a Todo task as a String.
     * @return String representation that include the type, status(marked) of the todo task.
     */
    @Override
    public String toString() {
        return this.type + super.getStatus();
    }
}

