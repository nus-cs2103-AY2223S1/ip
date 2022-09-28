package duke.types;

/**
 * A task that is specified by the user.
 *
 * @author Aaron Tan
 */
public abstract class Task {
    protected boolean isDone;
    private String description;

    /**
     * Constructor for a task. Note that Task is an
     * abstract class, therefore this constructor is only used by
     * its subclasses.
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns description of current task.
     *
     * @return Returns description of current task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns "X" if task is done and "" if not.
     *
     * @return Returns status of the task being done represented as a String.
     */
    public String getStatus() {
        return this.isDone ? "X" : "";
    }

    /**
     * Marks current task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks current task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String icon = this.getStatus();
        return String.format("[%s] %s", icon, this.description);
    }

    /**
     * Generates a String to be saved.
     *
     * @return String in the format of the more specific task.
     */
    public abstract String saveString();
}
