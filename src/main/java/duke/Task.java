package duke;

/**
 * General task class
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the correct status to be displayed based on
     * if the task is done or not.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void flip() {
        this.isDone = !isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        if (!this.isDone) {
            this.flip();
        }
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        if (this.isDone) {
            this.flip();
        }
    }

    /**
     * Outputs the correct string to be saved in save file.
     *
     * @return String of data to be stored.
     */
    public String toSaveData() {
        int num = isDone ? 1 : 0;
        return num + " | " + this.description;

    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
