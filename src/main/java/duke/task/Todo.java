package duke.task;

/**
 * Class to encapsulate an Task to be done.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param desc The String description of the Todo task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Converts task to String in format for output file.
     *
     * @return The task description for output text file.
     */
    @Override
    public String tofileString() {
        return "T|" + super.tofileString();
    }

    /**
     * Returns String representation of task.
     * Format is for output during program runtime.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
