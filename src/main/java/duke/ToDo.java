package main.java.duke;

/**
 * Represents a type of task called "ToDo"
 *
 * @author eugeneleong
 * @version 1.0
 */

public class ToDo extends Task {

    public ToDo(String action) {
        super(action);
    }

    /**
     * Returns the description of a ToDo task
     * @return [type = ToDo][marked?]
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
