package duke.task;

/**
 * Abstract class representing a Task.
 *
 * Includes fields and methods to be inherited that are common to all Task classes.
 */
public abstract class Task {
    protected String content;
    protected Boolean status;

    protected Task(String content) {
        this.content = content;
        this.status = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markComplete() {
        this.status = true;
    }

    public void unMarkComplete() {
        this.status = false;
    }

    /**
     * Marks this task as not completed.
     */
    @Override
    public String toString() {
        if (status) {
            return String.format("[x] %s", content);
        } else {
            return String.format("[ ] %s", content);
        }
    }

    public abstract String toFileData();
}
