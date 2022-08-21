package duke;

/**
 * Represents an Todo task, with no date or time.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int status) {
        this(description);
        isDone = status == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String parseToSaveData() {
        return "T" + "|" + super.parseToSaveData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
