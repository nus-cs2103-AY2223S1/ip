public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to initialise a Task object.
     *
     * @param description Task's outline.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns [X] if task is done. Else, [ ].
     *
     * @return String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns [X] if task is done. Else, [ ].
     *
     * @return String.
     */
    public void toggleIsDone(boolean bool) {
        isDone = bool;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}