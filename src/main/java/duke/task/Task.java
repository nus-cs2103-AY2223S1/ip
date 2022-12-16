package duke.task;

/**
 * Class to encapsulate a Task.
 */
public class Task {

    protected String desc;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param desc The String description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    /**
     * Returns the description of this task.
     * @return Description of task.
     */
    public String getDesc() {
        return this.desc;
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks current task as done.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Marks current task as not done.
     */
    public void undo() {
        this.isDone = false;
    }

    /**
     * Converts task to String in format for output file.
     *
     * @return The task description for output text file.
     */
    public String toFileFormat() {
        return this.isDone
                ? "1|" + this.desc
                : "0|" + this.desc;
    }

    /**
     * Returns String representation of task.
     * Format is for output during program runtime.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.desc;
    }
}
