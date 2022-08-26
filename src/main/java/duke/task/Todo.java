package duke.task;

/**
 * Represents a todo task
 */
public class Todo extends Task {

    /**
     * Constructs a todo task
     *
     * @param task_description Description of todo task
     */
    public Todo(String task_description) {
        super(task_description);
    }

    /**
     * Constructs a todo task
     *
     * @param task_description Description of todo task
     * @param isDone Status of todo task
     */
    public Todo(String task_description, boolean isDone) {
        super(task_description, isDone);
    }

    /**
     * Returns todo task
     *
     * @return todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
