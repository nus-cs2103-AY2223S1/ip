public abstract class Task {
    protected String description;
    protected boolean isDone;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + this.toString());
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + this.toString());
    }
}
