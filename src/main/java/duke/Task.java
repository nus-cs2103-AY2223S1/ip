package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns icon depending on whether task is done or not.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns true if task description contains the given input.
     *
     * @param input The String to search for.
     * @return Boolean if the input is found.
     */
    public boolean contains(String input) {
        return this.description.toLowerCase().contains(input.toLowerCase());
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns details of the task.
     *
     * @return Details of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
