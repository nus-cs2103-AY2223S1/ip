package duke.task;

public abstract class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n " + this.toString());
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n " + this.toString());
    }

    public void isDone(boolean status) {
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean contains(String keyword) {
        return taskDescription.contains(keyword);
    }

    public String toCsv() {
        int status = this.isDone ? 1 : 0;
        return status + "," + this.taskDescription;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.taskDescription;
    }
}
