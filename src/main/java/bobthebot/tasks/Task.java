package bobthebot.tasks;

public class Task {
    protected boolean isDone;
    protected String taskName;

    public Task(String name) {
        this.isDone = false;
        this.taskName = name.trim();
    }

    // method to mark a task as done
    public void markDone() {
        this.isDone = true;
    }

    // method to mark a task as undone
    public void markUndone() {
        this.isDone = false;
    }

    // method to get the status icon
    public String getStatusIcon() {
        return (isDone ? "✔" : " ");
    }

    // method to put the task in a format for storage
    public String toStorageFormat() {
        int done = this.isDone ? 1 : 0;
        String res = String.format("error | %d | %s", done, taskName);
        return res;
    }

    // method to return a string representation of a task
    @Override
    public String toString() {
        String res = "[" + getStatusIcon() + "] " + this.taskName;
        return res;
    }
}
