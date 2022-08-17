public class Task {
    /** The title or description of a task. **/
    protected String description;
    /** Indicates if a task is completed or not. **/
    protected boolean isDone;

    /**
     * The class constructor for a Task.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the isDone field of a Task and outputs the
     * corresponding status icon via a ternary operator.
     * @return a "X" or " " if task is completed or not respectively.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the isDone field of a Task to true and outputs the
     * corresponding formatted text with the task description.
     * @return a String to indicate the task has been marked done.
     */
    public String markAsDone() {
        this.isDone = true; // mark task as true
        String result = String.format("Nice! I've marked this task as done: \n%s", this.toString());
        return result;
    }

    /**
     * Retrieves the description of a task.
     * @return description of task.
     */
    public String getDescription() {
        return this.description; // get description of task
    }

    /**
     * Formats and outputs the string representation of a Task via
     * getStatusIcon() and getDescription().
     * @return formatted string representation of a Task.
     */
    public String toString() {
        String outputString = String.format("[%s] %s", getStatusIcon(), getDescription()); // format completion status and description String
        return outputString;
    }
}