package duke;

/**
 * This class encapsulates a To-do task.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class Todo extends Task {
    /**
     * Constructor for a to-do instance.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of the to-do.
     *
     * @return String representing this task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the string representation of this task for storage in a file.
     *
     * @return a String containing the task name, description and whether it is completed.
     */
    @Override
    public String encode() {
        return String.format("%s # %s",
                             "T",
                             super.encode());
    }
}
