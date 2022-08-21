public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void changeStatus(boolean isTaskDone, boolean showMessage) {
        isDone = isTaskDone;
        if (showMessage) {
            System.out.println("Task marked as " + (isTaskDone ? "" : "not ") + "done:\n\t" + this);
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
