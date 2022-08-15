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
        System.out.println("Helped you to mark already! Happy?\n"
                + this.toString());
    }

    public void unmark() {
        isDone = false;
        System.out.println("Troublesome... Unmarked for you already.\n"
                + this.toString());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
