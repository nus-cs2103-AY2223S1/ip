package duke.task;

/**
 * Encapsulates a task and tracks its completion status.
 */
public class Task {
    /**
     * Stores the description and completion status of the task.
     */
    private boolean done;
    private String name;

    /**
     * Constructor for a new Task item.
     * @param name Description of task
     */
    Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Constructor for a Task item with completion status.
     * @param name Description of task
     * @param done Whether task is done or not
     */
    Task (String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Retrieves the description of the Task.
     * @return Task description
     */
    public String getName() {
        return name;
    }

    /**
     * Renames the Task to the input name.
     * @param s New name of task
     */
    public void renameTask(String s) {
        this.name = s;
    }

    /**
     * Checks if Task is completed.
     * @return True if Task completed, False if not
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Marks the Task as completed.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Marks the Task as incomplete.
     */
    public void setUndone() {
        this.done = false;
    }

    /**
     * Returns a string representation of the Task instance.
     * @return String representation of Task
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return "[X] " + this.getName();
        } else {
            return "[ ] " + this.getName();
        }
    }

    /**
     * Returns the string representation of the task for saving to a file.
     * @return String representation of task to save to file
     */
    public String toStringWritable() {
        int done = this.isDone() ? 1 : 0;
        return String.format(" %d | %s ", done, this.getName());
    }
}
