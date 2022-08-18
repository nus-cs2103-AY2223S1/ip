public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String name) {
        this.isDone = false;
        this.taskName = name;
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
        return (isDone ? "X" : " ");
    }

    // method to return a string representation of a task
    @Override
    public String toString() {
        String res =  "[" + getStatusIcon() + "] " + this.taskName;
        return res;
    }
}
