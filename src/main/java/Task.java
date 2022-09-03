import java.io.FileWriter;
import java.io.IOException;

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
    }

    public void unmark() {
        this.isDone = false;
    }

    public String fileFormat() {
        return String.format("todo | %s | %b", description, isDone);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
