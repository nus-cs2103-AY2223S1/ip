package cheese.task;

/**
 * Represents a task with a description and complete/incomplete status.
 */
public class Task {
    /** Description of task. */
    private String description;

    /** Whether task is complete or incomplete. */
    private boolean isDone;

    /**
     * Constructs an instance of <code>Task</code>.
     * 
     * @param description Description of task.
     */
    protected Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Constructs an instance of <code>Task</code>.
     * 
     * @param isDone Whether task is complete or incomplete.
     * @param description Description of task.
     */
    protected Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    /**
     * Returns string representation of task to save in file.
     * 
     * @return String representation of task to save in file.
     */
    public String toFileString() {
        String isDoneString = isDone ? "T" : "F";
        return isDoneString + " // " + description;
    }

    /**
     * Returns string representation of task.
     * 
     * @return String representation of task.
     */
    @Override
    public String toString() {
        String checkbox = this.isDone ? "[X] " : "[ ] ";
        return checkbox + this.description;
    }
}
