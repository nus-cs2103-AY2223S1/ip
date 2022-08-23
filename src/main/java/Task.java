import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String format();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public static void writeToFile(String item) throws IOException {
        FileWriter fw = new FileWriter("data" + File.separator + "taskList.txt", true);
        fw.write(item + "\n");
        fw.close();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
