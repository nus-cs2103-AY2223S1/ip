package jean.task;

/**
 * Represents a Task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
        super.numberOfTasks += 1;
    }

    /**
     * Returns the String in the format to be saved.
     *
     * @return The formatted String to be saved.
     */
    public String getSaveData() {
        return "T|" + (super.isDone ? 1 : 0) + "|" + super.description;
    }

    /**
     * Returns the String representation of the Todo object.
     *
     * @return The formatted String to be displayed.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
