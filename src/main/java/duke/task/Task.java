package duke.task;

/**
 * Task is the abstract base for all the tasks that can be specified by the user.
 *
 * @author totsukatomofumi
 */
abstract public class Task {
    /** Description of the task. */
    private String description;

    /** Status of the task. */
    private Status status;

    /** Statuses a task can be in. */
    private enum Status {
        DONE,
        NOTDONE
    }

    /**
     * Constructs a task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        //for now, new tasks added assumed to be undone
        this.status = Status.NOTDONE;
    }

    /**
     * Mark this task as done.
     */
    public void mark() {
        this.status = Status.DONE;
    }

    /**
     * Unmark this task as done.
     */
    public void unmark() {
        this.status = Status.NOTDONE;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        switch (this.status) {
        case DONE:
            return "[X] " + this.description;
        case NOTDONE:
            return "[ ] " + this.description;
        default:
            return "";  //should not come here
        }
    }

    /**
     * Returns a string representation of the event that conforms to how storage
     * parses from the task history file.
     *
     * @return the parsable string representation of the task.
     */
    public String toData() {
        switch (this.status) {
        case DONE:
            return "1" + this.description.length() + "_" + this.description;
        case NOTDONE:
            return "0" + this.description.length() + "_" + this.description;
        default:
            return "";  //should not come here
        }
    }
}
