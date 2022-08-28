package task;

/**
 * todo is a Task that needs to be done without a specific date/time.
 */
public class ToDo extends Task {

    /**
     * Constructor for a todo that takes in the task description
     * @param description the specifics of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * stringify is used to store the tasks in a standard format in a storage file
     * @return string representation of todo to be stored
     */
    @Override
    public String stringify() {
        return "T##" + super.stringify();
    }

    /**
     * toString is used to print out the task in an easily readable format
     * @return string representation of a todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
