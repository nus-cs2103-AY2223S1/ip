package Duke;

/**
 * Duke.Task class to store the states whether task is complete or not
 */
public class Task {
    /**
     * boolean state to store completed or not.
     */
    private boolean completed;

    /**
     * name of task.
     */
    private final String taskName;


    /**
     * Constructor to initialize class.
     *
     * @param name task name
     */
    public Task(String name, boolean completed) {
        taskName = name;
        this.completed = completed;
    }

    /**
     * Sets complete to true.
     */
    public String mark() {
        completed = true;
        return String.format("     Nice! I've marked task %s as done:\n" +
                        "     " + this + "\n", taskName);
    }

    /**
     * Sets complete to false.
     */
    public String unMark() {
        completed = false;
        return String.format(
                        "     Ok, I've marked task %s as not done yet:\n" +
                        "     " + this + "\n", taskName);
    }

    /**
     * Printed message when object is created.
     */
    public String addMessage(int listSize) {
        return String.format("     added: %s\n", taskName);
    }

    /**
     * Gives task type.
     *
     * @return task type code.
     */
    public String taskType() {
        return "";
    }

    /**
     * Returns task name.
     *
     * @return task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns completed attribute.
     *
     * @return completed.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Returns the dates if possible.
     *
     * @return date.
     */
    public String getDate() {
        return "";
    }

    /**
     * Creates a String representation of Duke.Task.
     */
    public String toString() {
        String marked = "[ ]";
        if (completed) {
            marked = "[X]";
        }
        return marked + " " + taskName;
    }
}
