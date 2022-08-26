package duke;

/**
 * A class that creates the ToDo task.
 */
public class ToDo extends Task {

    /**
     * A constructor that initialises the ToDo object.
     *
     * @param description Describes the activity of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the description of the ToDo task.
     *
     * @return String that describes the activity of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
