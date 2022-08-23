/**
 * Represents a Todo task.
 *
 * @author WR3nd3
 */
public class Todo extends Task {

    protected String id = "[T]";

    /**
     * Constructor for the todo task.
     *
     * @param description String representing the description of the todo task.
     * @param isCompleted Boolean representing whether the task is completed.
     */
    public Todo(String description, Boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return a string consisting of the todo task's completion status and description.
     */
    @Override
    public String toString() {
        return id + super.toString();
    }
}