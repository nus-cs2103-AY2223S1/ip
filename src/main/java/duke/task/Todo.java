package duke.task;

public class Todo extends Task {
    /**
     * Creates a new undone task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     *
     * @return 'T'
     */
    @Override
    public char getType() {
        return 'T';
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
