/**
 * An object that represents a task that is input into the checklist.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Task {
    /**
     * The string description of the Task.
     */
    protected String description;

    /**
     * Boolean value on whether the Task has been marked as done or not.
     */
    protected boolean isDone;

    /**
     * A basic constructor to instantiate the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an "X" if Task is marked as done and " " if otherwise.
     *
     * @return The status icon of the Task depending on whether it is marked or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method that marks the Task as done.
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" + toString());
    }

    /**
     * Method that unmarks the Task as done.
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n" + toString());
    }

    /**
     * Method that returns the description of the Task.
     *
     * @return The description of the task along with its status.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
