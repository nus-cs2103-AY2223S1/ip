package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getFileIcon() {
        return (isDone ? "1" : "0");
    }

    public void mark() {
        this.isDone = true;
        System.out.println(String.format("Nice! I've marked this task as done:\n    %s\n", this));
    }

    public void unmark() {
        this.isDone = false;
        System.out.println(String.format("Ok, I've marked this task as not done yet:\n    %s\n", this));
    }

    @Override
    public String toString() {
        return String.format("%s", this.description);
    }

    public abstract String toFileString();
}
