package yilia.task;

/**
 * Represents a task that does not have a specific time or deadline.
 */
public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }
    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }
    /**
     * Returns the String representation.
     *
     * @return The String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns how the todo should appear on a file.
     *
     * @return The text information.
     */
    @Override
    public String parse() {
        return "T / " + (status() ? "1" : "0") + " / " + super.parse();
    }
    /**
     * Checks if two todos are the same.
     *
     * @param obj The other object to compare with.
     * @return A boolean value indicating if two tasks are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }
        return super.equals(obj);
    }
}
