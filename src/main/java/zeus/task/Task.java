package zeus.task;

/**
 * Class that represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task class.
     *
     * @param description A String that represents the Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor of Task class for copying.
     *
     * @param description A String that represents the Task description
     * @param isDone A boolean that indicates if Task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Copy Constructor of Task class
     *
     * @param task Task to be copied
     */
    public Task(Task task) {
        this(task.getDescription(), task.isDone);
    }

    public Task copy() {
        return new Task(this);
    }

    private String getStatus() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method that marks Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method that marks Task as not done.
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
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a String that represents the Task.
     *
     * @return a String that represents the Task
     */
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}

