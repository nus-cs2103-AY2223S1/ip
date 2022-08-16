public class Task {
    /* Name of task */
    protected String name;
    /* Flag representing if a task is completed */
    protected boolean isDone;

    /**
     * Constructor for Task Class.
     * @param name String representation of task name.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns string representation of Task object.
     * @return String representation of Task object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), name);
    }

    /**
     * Returns "X" if task is completed, " " otherwise.
     * @return String representation of completion status of Task object.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as uncompleted.
     */
    public void markAsNotdone() {
        this.isDone = false;
    }
}
