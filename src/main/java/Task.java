public class Task {
    private final String TaskItem;
    private boolean isMarked;

    public Task(String TaskItem) {
        this.TaskItem = TaskItem;
        this.isMarked = false;
    }

    @Override
    public String toString() {
        String checkbox = isMarked
                ? "[X] "
                : "[ ] ";
        return checkbox + this.TaskItem;
    }

    public void setIsMarked(boolean status) {
        isMarked = status;
    }
}