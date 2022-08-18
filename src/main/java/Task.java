package main.java;

/**
 * Task encapsulates the task description and its status of completion.
 * It supports toggling of task status.
 *
 * @author Totsuka Tomofumi
 * @version Level-4, Level-5
 */
public class Task {
    /**
     * Statuses a task can be in.
     */
    private enum Status {
        DONE,
        NOTDONE
    }

    /**
     * Description of task.
     */
    private String description;

    /**
     * Status of task.
     */
    private Status status;

    /**
     * Constructor for this task.
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        //for now, new tasks added assumed to be undone
        this.status = Status.NOTDONE;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.status = Status.DONE;
    }

    /**
     * Unmarks this task as done.
     */
    public void unmark() {
        this.status = Status.NOTDONE;
    }

    /**
     * Returns a string representation of the task.
     * @return Task status and its description
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
}
