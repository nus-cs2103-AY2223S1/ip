package duke;

/**
 * Todo class represents a type of task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param description the String that describes the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task in a specific format to save it in the text file.
     *
     * @return the string that represents the task in a specific format
     */
    @Override
    public String getDataFormat() {
        return String.format("T // %s // %s", getStatusIcon(), description);
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return a string that represents the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
