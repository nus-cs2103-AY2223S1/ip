package jude.task;

/**
 * The {@code Task} class models a task. A task has a description and an indicator depicting whether
 * it has been done.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new {@code Task} object with a given description and whether it has been done.
     *
     * @param description The description of the {@code Task}.
     * @param isDone Whether the {@code Task} is marked as done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns whether the given {@code Task} object is marked as done.
     *
     * @return Whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the {@code Task} object.
     *
     * @return Description of the {@code Task} object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets whether the {@code Task} is done.
     *
     * @param isDone Whether the {@code Task} is done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the status icon depicting whether the {@code Task} has been done.
     *
     * @return 'X' if {@code Task} is done, ' ' otherwise.
     */
    //@@author cheeheng-reused
    //Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
    //with minor modifications
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the status of the {@code Task} to 'done'.
     */
    public void markAsDone() {
        this.setIsDone(true);
    }

    /**
     * Sets the status of the {@code Task} to 'not done'.
     */
    public void markAsUndone() {
        this.setIsDone(false);
    }

    /**
     * Returns the task type code of the {@code Task} object. The task type code will be displayed
     * on the command line interface.
     *
     * @return The task type code of the {@code Task} object.
     */
    public abstract String getTaskTypeCode();

    /**
     * Returns the String representation of the {@code Task} object, i.e.
     *   a string in the format "[task type code][get status icon] description".
     *
     * @return String representation of the {@code Task} object.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskTypeCode(), this.getStatusIcon(),
                this.description);
    }

    /**
     * Returns a String representation of the {@code Task} object which in a format convenient to
     * save and load files.
     *
     * The string returned is in the following format (with newlines in between components and in
     * the end):
     * Task Type Code
     * Description
     * 1 if the task is done, and 0 otherwise
     *
     * @return The String representation of the {@code Task} object.
     */
    public String toFileSaveString() {
        return String.format("%s\n%s\n%d\n", getTaskTypeCode(), description, isDone ? 1 : 0);
    }
}
