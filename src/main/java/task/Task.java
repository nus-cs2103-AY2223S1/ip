package task;

/**
 * The Task class encapsulates Task objects
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class Task {
    private final String taskName;
    private boolean isDone = false;
    private String priority = "[ ]";

    /**
     * Constructor for Task Object
     * @param taskName the string representation of task to be done
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks a Task as done
     *
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a Task as undone
     *
     */
    public void unmark() {
        this.isDone = false;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Returns a String object representing this Task's value.
     *
     * @return the string representation of the specified Task
     */
    @Override
    public String toString() {
        String ticker = "[ ]";

        if (isDone == true) {
            ticker = "[X]";
        }

        return ticker + priority + this.taskName;
    }
}
