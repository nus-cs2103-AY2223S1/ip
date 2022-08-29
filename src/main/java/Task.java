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

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public void writeToFile(FileWriter writer) throws IOException {
        writer.write(String.format("T;%s;%s\n", getStatusIcon(), description));
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
