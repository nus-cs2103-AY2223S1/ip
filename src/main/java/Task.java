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
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
