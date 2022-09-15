package duke.tasks;

/*
 * Task is an abstract class that represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /*
     * Constructs a Task object.
     * 
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * Constructs a Task object.
     * 
     * @param description The description of the task.
     * 
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /*
     * Returns the status icon of the task.
     * 
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /*
     * Returns the description of the task.
     * 
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /*
     * Marks the status of the task as done
     */
    public void setDone() {
        this.isDone = true;
    }

    /*
     * Marks the status of the task as undone
     */
    public void setUndone() {
        this.isDone = false;
    }

    /*
     * Returns the text output representation of the task to users.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /*
     * Returns the text output representation of the task to be saved in the
     * storage.
     */
    public String save() {
        return "shouldn't be saved";
    }
}
