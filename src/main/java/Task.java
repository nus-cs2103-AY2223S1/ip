/**
 * Task is a representation of a task in Duke.
 */
public class Task {
    /** Description of the task. */
    private final String description;
    /** Whether the task is done. */
    private boolean isDone;

    /**
     * Constructor for a Task, with a description.
     * Task is set as "not done" when created.
     *
     * @param description Description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Toggles whether a task is done.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Gets the string representation of a task.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        String doneIcon = this.isDone ? "X" : " " ;
        return String.format("[%s] %s", doneIcon, this.description);
    }
}
