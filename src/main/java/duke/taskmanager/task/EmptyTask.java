package duke.taskmanager.task;

public class EmptyTask extends Task {
    /**
     * Creates an empty task with an empty task name. Used for exception handling.
     */
    public EmptyTask() {
        super("");
    }

    /**
     * Returns whether the task is empty. Returns true as this is an empty task.
     *
     * @return true
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * Returns an empty string as this is an empty task.
     *
     * @return empty string
     */
    @Override
    public String getFormattedString() {
        return "";
    }
}
