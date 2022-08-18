public class Task {
    protected String description;
    protected boolean isDone;
    protected String colour;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.colour = ANSI_RED;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format(this.colour + "[%s] %s" + ANSI_RESET, this.getStatusIcon(), this.description);
    }

    public void markAsDone() {
        this.isDone = true;
        this.colour = ANSI_GREEN;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + this.toString());
    }

    public void markAsUndone() {
        this.isDone = false;
        this.colour = ANSI_RED;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + this.toString());
    }
}
