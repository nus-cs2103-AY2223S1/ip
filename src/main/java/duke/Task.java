package duke;

/** Represents a Task. */
public class Task {
    enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Creates a Task.
     *
     * @param description Describes the activity of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return String that indicates if the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes the status of the task to show that it is not done.
     */
    public void unMarkTask() {
        this.isDone = false;
    }

    /**
     * Sets the priority of the task based on user input.
     *
     * @param priority String representation of the desired priority.
     */
    public void setPriority(String priority) {
        try {
            switch (priority) {
            case "high":
                this.priority = Priority.HIGH;
                break;
            case "medium":
                this.priority = Priority.MEDIUM;
                break;
            case "low":
                this.priority = Priority.LOW;
                break;
            default:
                throw new DukeException("Invalid Priority Input!");
            }
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    /**
     * Gets priority of the task.
     *
     * @return String representation of the priority of the task.
     */
    public String getPriority() {
        //Return blank space if no priority is set
        if (this.priority == null) {
            return " ";
        }

        //Return strings according to the respective priority values
        try {
            switch (this.priority) {
            case HIGH:
                return "H";
            case MEDIUM:
                return "M";
            case LOW:
                return "L";
            default:
                throw new DukeException("Invalid Priority!");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + "[" + this.getPriority() + "] " + this.description;
    }
}
