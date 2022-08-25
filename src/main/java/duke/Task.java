package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets task as !done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns a task data to be saved in duke.txt
     * @return
     */
    public String toWrite() {
        return "";
    }

    /**
     * Returns a task status (done or !done) as a String icon
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a task type as a String icon
     * @return
     */
    public String getTaskTypeIcon() { return "T"; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}