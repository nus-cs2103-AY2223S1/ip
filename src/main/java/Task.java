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
        isDone = true;
        System.out.println("Nice! I've marked this task as done:\n"
                + this.toString());
    }

    public void unmark() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n"
                + this.toString());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
