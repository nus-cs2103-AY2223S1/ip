public class Task {
    private String TaskItem;
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

    public String setIsMarked(boolean status) {
        isMarked = status;
        return this.toString();
    }
}