
public class Task {

    protected final String description;
    protected final boolean isDone;


    // Constructors
    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public Task markAsDone() {
        return new Task(this.description, true);
    }


    public Task markAsUndone() {
        return new Task(this.description, false);
    }


    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }


    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
