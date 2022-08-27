package duke;

/**
 * This class encapsulates the attributes, states and capabilities
 * of a task: its name, its done status and the ability to change it.
 *
 * @author Siau Wee
 *
 */
public class Task {

    /** The name of the task. */
    private String taskName;

    /**
     * The status of the task; false indicates that
     * the task is undone and true indicates that
     * the task is done.
     */
    private Boolean isMarked = false;

    /**
     * The constructor to initialise a new Task object with a given name.
     *
     * @param taskName The name of the task to be created.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns the current status (true for marked, false for unmarked)
     * of the task
     * @return True if marked done, False if marked undone
     */
    public Boolean getStatus() {
        return this.isMarked;
    }

    /**
     * Checks if the current task's name contains a specified sequence of chars.
     * 
     * @param chars The sequence of chars to match with task name
     * @return True if yes, False if no
     */
    public Boolean doesNameContain(String chars) {
        return this.taskName.contains(chars);
    }

    /**
     * Marks the current Task object as done.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Marks the current Task object as undone.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Returns the string representation of the current Task object.
     *
     * @return Indicator for the task's done status as well as its name
     */
    @Override
    public String toString() {
        if (this.isMarked) {
            return "[X] " + this.taskName;
        }
        return "[ ] " + this.taskName;
    }
}
