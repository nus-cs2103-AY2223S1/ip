/**
 * Creates a new task
 */
package Tasks;

public class Task {
    private boolean isDone = false;
    private String taskName;

    /**
     * Constructor which gets the task name from child classes
     *
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Sets the task as done
     */
    public void setMarked() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done
     */
    public void setUnmarked() {
        this.isDone = false;
    }

    /**
     * Gets the icon for printing if task is done or not done
     *
     * @return String that contains how to correctly print a task
     *         that is done or not done
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the task name of the specific task
     *
     * @return String that contains that task's name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the date assigned by the user in the input
     *
     * @return String that contains the correct date format in specific tasks only
     *         (in deadline and event tasks classes)
     */
    public String getDateline() {
        return "";
    }

    /**
     * Prints the correct output for each task
     *
     * @return String that contains the task name and whether the task is marked or not
     */
    public String toString() {
        return  String.format("%s %s", getStatusIcon(), this.taskName);
    }
}
