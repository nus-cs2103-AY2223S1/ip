package duke;

import java.io.Serializable;

/**
 * A basic task with no added features.
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public class Todo extends Task implements Serializable {

    /**
     * Constructor to create a new duke.Deadline
     *
     * @param task the task that you want to complete (String)
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * To String method that returns the task in string form to the user
     *
     * @return the task in string format
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
