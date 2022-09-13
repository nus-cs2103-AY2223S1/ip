package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    public static final String TASK_TYPE = "T";

    /**
     * Constructs a todo task.
     *
     * @param taskDescription Description of todo task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Constructs a todo task.
     *
     * @param taskDescription Description of todo task.
     * @param isDone Status of todo task.
     */
    public Todo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    /**
     * Returns todo task.
     *
     * @return todo task.
     */
    @Override
    public String toString() {
        return "[" + Todo.TASK_TYPE + "]" + super.toString();
    }
}
