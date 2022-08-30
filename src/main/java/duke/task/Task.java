package duke.task;

/**
 * Class for Task.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class Task {
    /**
     * The details of the task.
     */
    protected String description;
    /**
     * The status of the task.
     */
    protected boolean isDone;

    /**
     * A constructor to initialize Task.
     *
     * @param description The details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the string format of the status of the task.
     *
     * @return String format of the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Change the status of the task to completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Change the status of the task to uncompleted.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Get the string format of the task which will be written in the file.
     *
     * @return String format of the task which will be written in the file.
     */
    public String getStringToSave() {
        return "";
    }

    /**
     * Get string format of the task.
     *
     * @return Task in a string format.
     */
    @Override
    public String toString() {
        String s = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return s;
    }
}
