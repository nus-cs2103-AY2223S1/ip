package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToFileFormat() {
        return String.format("todo | %s | %b", super.description, super.isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
