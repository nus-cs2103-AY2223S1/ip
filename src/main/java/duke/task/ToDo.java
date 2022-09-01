package duke.task;

public class ToDo extends Task {
    /**
     * Constructs a to-do with the specified description.
     *
     * @param description The specified description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a to-do using the storage data.
     *
     * @param line The line representing the to-do in the storage.
     * @return The to-do with the specified description.
     */
    public static ToDo createToDoFromString(String line) {
        return new ToDo(line.substring(10));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isOnThisDate(String dateStr) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
