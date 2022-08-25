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
    public Task(String name, boolean init, boolean completed, int listSize) {
        taskName = name;
        this.completed = completed;
    }

    /**
     * Sets complete to true.
     */
    public void mark() {
        completed = true;
        System.out.printf(
                "    ____________________________________________________________\n" +
                        "     Nice! I've marked task %s as done:\n" +
                        "     " + this + "\n" +
                        "    ____________________________________________________________\n", taskName);
    }

    /**
     * Sets complete to false.
     */
    public void unMark() {
        completed = false;
        System.out.printf(
                "    ____________________________________________________________\n" +
                        "     Ok, I've marked task %s as not done yet:\n" +
                        "     " + this + "\n" +
                        "    ____________________________________________________________\n", taskName);
    }

    /**
     * Printed message when object is created.
     */
    public void addMessage(int listSize) {
        System.out.printf(
                "    ____________________________________________________________\n" +
                        "     added: %s\n" +
                        "    ____________________________________________________________\n", taskName);
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
