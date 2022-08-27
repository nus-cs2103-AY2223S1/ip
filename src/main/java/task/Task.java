package task;

/**
 * Represents the tasks that are stored within the internal duke list
 *
 * @author Bryan Lim Jing Xiang
 */
public abstract class Task {
    private final String taskItem;
    private boolean isMarked;

    /**
     * @param taskItem Task to be stored
     */
    public Task(String taskItem) {
        this.taskItem = taskItem;
        this.isMarked = false;
    }

    /**
     * {@inheritDoc}
     *
     * @return String representation of the task including its markStatus and type
     */
    @Override
    public String toString() {
        String checkbox = isMarked ? "[X] " : "[ ] ";
        return checkbox + this.taskItem;
    }

    public void setIsMarked(boolean status) {
        isMarked = status;
    }

    protected boolean getIsMarked() {
        return isMarked;
    }

    protected String getTaskItem() {
        return taskItem;
    }

    /**
     * @return A string encoding of the task to be stored in the save file
     */
    public abstract String encode();
}
