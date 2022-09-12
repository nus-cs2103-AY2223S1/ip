package duke.task;

public abstract class Task {
    String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean doesContain(String keyword) {
        return description.contains(keyword);
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Mark the task as done, and returns a message indicating task is marked successfully.
     * If the task is already marked as done, an error message is returned instead.
     */
    public String markAsDone() {
        if (isDone) {
            return "This task is already marked as done!";
        } else {
            this.isDone = true;
            return "Nice! I've marked this task as done:\n" + this;
        }
    }

    /**
     * Mark the task as not done yet, and returns a message indicating task is unmarked successfully.
     * If the task is already marked as not done yet, an error message is returned instead.
     */
    public String markAsUndone() {
        if (!isDone) {
            return "This task is already marked as not done yet!";
        } else {
            this.isDone = false;
            return "OK, I've marked this task as not done yet:\n" + this;
        }
    }

    /**
     * Returns task written in file format for easier parsing during decoding.
     */
    public abstract String convertToFileFormat();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
