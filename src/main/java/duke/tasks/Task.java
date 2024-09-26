package duke.tasks;

public class Task {

    private boolean isDone;
    private final String DESCRIPT;

    /**
     * Creates the Task instance.
     * @param description
     */
    public Task(String description) {
        this.DESCRIPT = description;
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
        return this.DESCRIPT;
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + this.DESCRIPT);
    }
}

