package amanda.task;

/**
 * To-do is a simple task with a description and no time associated with it.
 */
public class Todo extends Task {

    /**
     * Constructor for To-do class.
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
