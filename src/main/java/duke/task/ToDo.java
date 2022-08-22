package duke.task;

/**
 * ToDo class that stores the Description and State of the ToDo Task.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class ToDo extends Task {

    /**
     * Constructor for Todo Task.
     *
     * @param description Description of the Todo Task.
     */
    public ToDo(String description) {
        super(description, "T");
    }

    /**
     * Constructor for Todo Task.
     *
     * @param description Description of the Duke.Task.ToDo Task.
     * @param done Completeness of Duke.Task.ToDo Task.
     */
    public ToDo(String description, String done) {
        super(description, done, "T");
    }

    /**
     * Returns string representation of a Duke.Task.ToDo Task.
     *
     * @return String Representation of a Duke.Task.ToDo Task.
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString();
    }
}
