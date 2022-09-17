package duke;
/**
 * Represents a todo that can be created by the user
 */
public class Todo extends Task {
    /**
     * Constructor used to create a new todo object
     * @param s Command containing task message and time
     * @param isCompleted whether the task is completed
     */
    public Todo (String s, boolean isCompleted) {
        super(s.substring(5), isCompleted);
    }

    @Override
    public String toString() {
        String completion = this.isComplete() ? "[X]" : "[ ]";
        return "[T]" + completion + " " + this.getTaskName();
    }

    /**
     * Returns a formatted version of the task to store in memory
     * @return formatted string representation of task
     */
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "T | " + completion + " | " + this.getTaskName();
    }
}
