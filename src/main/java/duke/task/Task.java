package duke.task;

/**
 * Class that represents a task.
 */
public class Task {
    /** Description of the task */
    protected String desc;
    /** Completion status of the task */
    protected boolean isDone;

    /**
     * Constructor to initialize the description and completion status of the task.
     * Completion status is always false when task is first created.
     * 
     * @param desc The task description
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Constructor to initialize the description and completion status of the task.
     * 
     * @param desc The task description.
     * @param isDone The task completion status.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Gets the string representation of the task status.
     * 
     * @return The string representation of the task status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as complete.
     */
    public void mark() {
        // change to return a boolean if status has changed
        // move response to Ui class as showMarkResponse method
        if (this.isDone) {
            System.out.println("  You can't finish the same task twice, genius.");
        } else {
            System.out.println("  You really took your time with this one, didn't you?");
            this.isDone = true;
        }
        System.out.println("    " + this);
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        // change to return a boolean if status has changed
        // move response to Ui class as showUnmarkResponse method
        if (!this.isDone) {
            System.out.println("  You're trying to unmark a task you haven't done.\n"
                    + "  Let that sink in for a moment.");
        } else {
            System.out.println("  And here I was thinking you were getting somewhere...");
            this.isDone = false;
        }
        System.out.println("    " + this);
    }

    /**
     * Returns the task as a string in its saved format.
     * @return The string representation of the saved format of the task.
     */
    public String getSaveFormat() {
        return "| " + (isDone ? 1 : 0) + " | " + this.desc;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.desc;
    }
}
