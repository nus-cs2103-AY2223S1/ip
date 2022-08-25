package Duke;

public class Task {

    private boolean isDone;
    private String descript;

    /**
     * Creates the Task instance.
     * @param description
     */
    public Task(String description) {
        this.descript = description;
        this.isDone = false;
    }

    /**
     * Gets the marked status of the Task.
     * @return "X" if is marked.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the date to be done on the task.
     * @return date.
     */
    public String getDate() {
        return "";
    }

    /**
     * Gets the details of task.
     * @return detail.
     */
    public String getDescription() {
        return this.descript;
    }

    /**
     * Marks task as done
     * @return the task to print
     */
    public String markAsDone() {
        this.isDone= true;
        return("Nice! I've marked this task as done:\n  " + this);
    }

    /**
     * Marks task as not done
     * @return the task to print
     */
    public String markAsNotDone() {
        this.isDone= false;
        return("OK, I've marked this task as not done yet:\n  " + this);
    }

    @Override
    public String toString() {
        return("[" + getStatusIcon() + "] " + this.descript);
    }
}

