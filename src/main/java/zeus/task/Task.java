package zeus.task;

/**
 * Class that represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param description A String that represents the Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task for copying.
     *
     * @param description A String that represents the Task description.
     * @param isDone A boolean that indicates if Task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a copy of Task.
     *
     * @param task Task to be copied.
     */
    public Task(Task task) {
        this(task.getDescription(), task.isDone);
    }

    /**
     * Returns a copy of the Task.
     *
     * @return A copy of the Task.
     */
    public Task copy() {
        return new Task(this);
    }

    private String getStatus() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns Task as a String formatted to be a line in file.
     *
     * @return Task in the format of a line in file.
     */
    public String getFileFormat() {
        return String.format("X | %d | %s", isDone ? 1 : 0, this.description);
    }

    /**
     * Returns Task description.
     *
     * @return String representing the Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a String that represents the Task.
     *
     * @return a String that represents the Task.
     */
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}

