package duke;

/**
 * A task class to indicate a ToDo task,
 * only requires a description of task
 *
 */
public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns string representation of Todo task
     * @return String representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}