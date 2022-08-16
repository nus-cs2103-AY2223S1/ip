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
    private Boolean status = false;

    /**
     * The constructor to initialise a new Task object with a given name.
     *
     * @param taskName The name of the task to be created.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks the current Task object as done.
     */
    public void mark() {
        this.status = true;
        System.out.println("Marked this task as done:");
        System.out.println(this);
    }

    /**
     * Marks the current Task object as undone.
     */
    public void unmark() {
        this.status = false;
        System.out.println("Marked this task as undone:");
        System.out.println(this);
    }

    /**
     * Returns the string representation of the current Task object.
     *
     * @return Indicator for the task's done status as well as its name
     */
    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.taskName;
        }
        return "[ ] " + this.taskName;
    }
}
