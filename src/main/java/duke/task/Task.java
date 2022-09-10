package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Mark the task as done if not done yet.
     * If the task is already marked as done, an error message is output.
     */
    public void markAsDone() {
        if (isDone) {
            System.out.println("This task is already marked as done!");
        } else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n" + this);
        }
    }

    /**
     * Mark the task as done without printing any message.
     */
    public void markAsDoneWithoutMessage() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done yet if done.
     * If the task is already marked as not done yet, an error message is output.
     */
    public void markAsUndone() {
        if (!isDone) {
            System.out.println("This task is already marked as not done yet!");
        } else {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n" + this);
        }
    }

    public abstract String convertToFileFormat();

    public boolean doesContain(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns string representation of this task.
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
