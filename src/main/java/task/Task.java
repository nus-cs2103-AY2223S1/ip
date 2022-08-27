package task;

/**
 * Represents the tasks that are stored within the internal duke list
 *
 * @author Bryan Lim Jing Xiang
 */
public abstract class Task {
    private final String TaskItem;
    private boolean isMarked;

    /**
     * @param TaskItem Task to be stored
     */
    public Task(String TaskItem) {
        this.TaskItem = TaskItem;
        this.isMarked = false;
    }

    /**
     * {@inheritDoc}
     *
     * @return String representation of the task
     * including its marked status and type
     */
    @Override
    public String toString() {
        String checkbox = isMarked ? "[X] " : "[ ] ";
        return checkbox + this.TaskItem;
    }

    public void setIsMarked(boolean status) {
        isMarked = status;
    }

    protected boolean getIsMarked() {
        return isMarked;
    }

    protected String getTaskItem() {
        return TaskItem;
    }

    /**
     * @return A string encoding of the task to be stored in the save file
     */
    public abstract String encode();
}
