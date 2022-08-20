/**
 * A class that stores something to do.
 */
public class ToDo extends Task {

    /**
     * Creates a task item.
     * @param description Description of task.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Creates a task item.
     * @param description Description of task.
     * @param done If the task is done.
     */
    public ToDo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Get a string array representation suitable for printing to files.
     * @return String array representation.
     */
    @Override
    public String[] getAsStringArray() {
        String[] data = super.getAsStringArray();
        return new String[]{ "ToDo", data[1], data[2] };
    }
}
