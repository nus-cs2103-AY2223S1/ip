public class ToDo extends Task {
    /**
     * A public constructor to initialise a ToDo task.
     * @param description The details of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}