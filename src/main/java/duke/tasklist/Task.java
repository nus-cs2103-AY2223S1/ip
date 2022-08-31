package duke.tasklist;

/**
 * Class to represent non-specific tasks.
 */
public class Task {

    /** String representing the description of task */
    protected String description;
    /** Boolean representing whether the task has been completed */
    protected boolean isDone;

    /**
     * Constructor for a task object.
     * @param description Description of the created task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as completed and handles if already marked.
     */
    public void markAsDone() {
        if (this.isDone) {
            System.out.println("Task already marked as done\n" + this);
        } else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done\n"
                    + this);
        }
    }

    /**
     * Marks tasks read from file as completed.
     */
    public void markSavedTaskAsDone() {
        if (!this.isDone) {
            this.isDone = true;
        }
    }

    /**
     * Marks tasks as not completed.
     */
    public void markAsNotDone() {
        if (!this.isDone) {
            System.out.println("Task already marked as not done\n" + this);
        } else {
            this.isDone = false;
            System.out.println("Ok! I've unmarked this task\n" + this);
        }
    }

    /**
     * Return string representing the status of a task's completion.
     * @return X if task is done, whitespace if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Return string representation of task object for saving to file.
     */
    public String getSavedFileFormat() {
        return (this.isDone ? 1 : 0) + "|" + this.description;
    }

    /**
     * Return string representation of task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
