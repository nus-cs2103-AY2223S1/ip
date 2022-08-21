import exceptions.TaskDescriptionEmpty;

public abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Checks whether fields are valid. To be called when creating Task via factory methods.
     * @throws TaskDescriptionEmpty
     */
    protected void validate() throws TaskDescriptionEmpty {
        if (description.equals("")) throw new TaskDescriptionEmpty();
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " "; // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
