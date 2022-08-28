package uwu.task;

/**
 * Represents a task of type ToDos.
 */
public class ToDos extends Task {
    /**
     * Constructor for ToDos object.
     *
     * @param description The description of the ToDos task.
     */
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        String isDoneIndicator = super.isDone ? "1" : "0";

        return "T," + isDoneIndicator + "," + description.trim();
    }
}
