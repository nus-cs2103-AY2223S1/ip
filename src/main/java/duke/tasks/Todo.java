package duke.tasks;

/**
 * Represents a Todo with no specific timeframe
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Generates an encoding of the Task for use in storage
     * @return encoded string following the storage format
     */
    public String getStorageString() {
        return "T" + "|" + (isDone ? "1" : "0") + "|" + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}