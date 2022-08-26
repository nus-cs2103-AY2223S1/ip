package duke.task;

/**
 * Represents a task.Todo task.
 *
 * @author WR3nd3
 */
public class Todo extends Task {


    /**
     * Constructor for the todo task.
     *
     * @param description String representing the description of the todo task.
     * @param isCompleted boolean representing whether the task is completed.
     */
    public Todo(String description, boolean isCompleted) {
        super(Task_Id.T, description, isCompleted);
    }

    /**
     * {@inheritDoc}
     */
    public String summary() {
        String status = isCompleted ? "1" : "0";
        String message = id + " | " + status + " | " + description;
        return message;
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return a string consisting of the todo task's completion status and description.
     */
    @Override
    public String toString() {
        return "[" + id + "] " + super.toString();
    }
}