package task;

public abstract class Task {
    private final String taskItem;
    private boolean isMarked;

    public Task(String taskItem) {
        this.taskItem = taskItem;
        this.isMarked = false;
    }

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

    public abstract String encode();
}
