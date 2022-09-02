package main.java.amanda.task;

/**
 * Task is something that the user wants to get done.
 */
public class Task {
    protected String description;
    protected TaskState state;

    /**
     * Constructor for Task class.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.state = TaskState.NOT_DONE;
    }

    /**
     * Convert the done/not done status of the task into a "X" or " " symbol for printing.
     * @return "X" if task is done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (state == TaskState.DONE ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the state of the task as done.
     */
    public void doTask() {
        state = TaskState.DONE;
    }

    /**
     * Mark the state of the task as not done.
     */
    public void undoTask() {
        state = TaskState.NOT_DONE;
    }

    /**
     * Get the description of the task.
     * @return the description of the task.
     */
    public String getTask() {
        return this.description;
    }

    /**
     * Get the state of the task.
     * @return "1" if the task is done, "0" if the task is not done.
     */
    public String getState() {
        return state == TaskState.DONE ? "1" : "0";
    }

    /**
     * Get the time associated with this event.
     * @return time associated with this event.
     */
    public String getTime() {
        return (this instanceof Deadline) ? ((Deadline) this).getBy() : ((Event) this).getBy();
    }

    /**
     * Get the type of task.
     * @return "T" if task is a to-do, "D" if task is a Deadline, "E" if task is an event.
     */
    public String getType() {
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else {
            return "E";
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }
}