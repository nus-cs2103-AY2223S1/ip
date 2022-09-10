package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String convertToFileFormat() {
        return String.format("todo | %s | %b", super.description, super.isDone);
    }

    /**
     * Returns string representation of this task.
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
