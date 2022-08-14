public class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected static int numberOfTasks = 0;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n [X] " + this.taskDescription;
    }

    public String markAsNotDone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n [ ] " + this.taskDescription;
    }

    public String addedString() {
        return "added: " + this.taskDescription;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.taskDescription;
    }
}
