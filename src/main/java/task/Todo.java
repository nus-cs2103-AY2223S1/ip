package task;

/**
 * Represents a Todo Task object.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Todo extends Task {

    /**
     * Initializes Todo Task object with the task description.
     *
     * @param name Task name to be stored
     */

    public Todo(String name) {
        super(name);
    }

    /**
     * Converts the task into a string to be stored in the Storage class.
     *
     * @return the String containing the Task information to be stored
     */

    @Override
    public String stringify() {
        return "T##" + super.stringify();
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return the String representation of the Task
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
