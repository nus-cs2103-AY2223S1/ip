package duke.task;

/**
 * Represents a Todo Task
 */
public class Todo extends Task {

    protected String by;
    private final static String ICON = "T";

    /**
     * Instantiates a new Todo task
     */
    public Todo(String description) {
        super(description, ICON);
    }


    /**
     * Returns the string format of a Todo task
     *
     * @return Returns String format of a Todo task
     */
    @Override
    public String toString() {
        return String.format("[T]" + "[%s] " + super.toString(), super.getStatusIcon());
    }

    /**
     * Returns the string format for saving into a file
     *
     * @return String format for saving into a file
     */
    public String toSave() {
        return String.format("[T]" + "[%s] " + super.toString(), super.getStatusIcon());
    }
}