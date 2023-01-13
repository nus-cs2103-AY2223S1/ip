package duke.task;

/**
 * Abstract class representing a Task.
 *
 * Includes fields and methods to be inherited that are common to all Task classes.
 */
public abstract class Task {
    protected String content;
    protected boolean isDone;
    protected String tag;

    protected Task(String content) {
        this.content = content;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Marks this task as completed.
     */
    public void markComplete() {
        this.isDone = true;
    }

    public void unMarkComplete() {
        this.isDone = false;
    }

    public void addTag(String tag) {
        this.tag = '#' + tag;
    }

    public void loadTag(String tag) {
        this.tag = tag;
    }

    /**
     * Marks this task as not completed.
     */
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[x] %s", content);
        } else {
            return String.format("[ ] %s", content);
        }
    }

    public abstract String toFileData();
}
