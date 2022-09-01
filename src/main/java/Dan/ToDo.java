package dan;

/**
 * One of the task types. The simplest form of task.
 */
public class ToDo extends Task {
    private static final String ICON = "T";

    /**
     * Creates a ToDo task with its associated description.
     *
     * @param description The description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toDataString(String separator) {
        return String.format("%s%s", ICON, super.toDataString(separator));
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", ICON, super.toString());
    }
}
