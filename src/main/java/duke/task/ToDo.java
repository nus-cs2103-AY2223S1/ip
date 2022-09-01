package duke.task;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    /**
     * Constructor for to-do.
     *
     * @param detail String
     */
    public ToDo(String detail) {
        super(detail);
    }

    /**
     * Constructor for to-do.
     *
     * @param detail String
     */
    public ToDo(String detail, boolean isDone) {
        super(detail, isDone);
    }

    /**
     * Returns String form of the task
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String to be stored in the hardware list.
     * @return String
     */
    @Override
    public String storedData() {
        return "T" + "|" + super.storedData();
    }
}
