package duke.task;

/**
 * A class that encapsulates the Todo task.
 */
public class ToDo extends Task{

    /**
     * The class constructor.
     *
     * @param description The description of the event.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string to be saved in the text file to be
     * loaded in later.
     *
     * @return The string identifying the details of the task.
     */
    @Override
    public String stringToSave() {
        return "T|" + ("X".equals(super.getStatusIcon()) ? "1|" : "0|") + super.description;
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
