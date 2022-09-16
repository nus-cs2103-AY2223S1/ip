package ip.task;

/**
 * Abstract class describing a task object.
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isComplete;

    public Task() {
        isComplete = false;
    }

    public abstract String formatToSave();
    public abstract boolean containsString(String keyword);

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void markComplete() {
        isComplete = true;
    }

    public void markIncomplete() {
        isComplete = false;
    }


    private String getCheckbox() {
        return isComplete ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getCheckbox() + " " + taskDescription;
    }
}
