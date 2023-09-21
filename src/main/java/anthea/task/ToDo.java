package anthea.task;

/**
 * A class that stores something to do.
 */
public class ToDo extends Task {

    /**
     * Creates a task item.
     *
     * @param description Description of task.
     */
    public ToDo(String description) {
        this(description, false);
        assert description != null;
    }

    /**
     * Creates a task item.
     *
     * @param description Description of task.
     * @param isDone If the task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        assert description != null;
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Gets a string array representation suitable for printing to files.
     *
     * @return String array representation.
     */
    @Override
    public String[] getAsStringArray() {
        String[] data = super.getAsStringArray();
        assert data != null;
        return new String[]{ "ToDo", data[1], data[2] };
    }
}
