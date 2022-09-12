package Duke.Task;

public abstract class Task {

    protected static String PRINT_TIME_FORMAT = "EE, dd MMM yyyy, HH:mm";
    protected static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HHmm";

    private final String description;
    private boolean isDone = false;
    private final String shorthand;

    Task(String description, String shorthand) {
        this.description = description;
        this.shorthand = shorthand;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStorageString() {
        return String.format("%s|%s|%s",
                getShorthand(),
                getStatusIcon(),
                getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                getShorthand(),
                getStatusIcon(),
                getDescription());
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private String getDescription() {
        return this.description;
    }

    private String getShorthand() {
        return this.shorthand;
    }
}