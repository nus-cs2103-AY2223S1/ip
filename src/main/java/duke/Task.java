package duke;
import java.io.*;
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String GetStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void SetDone() {
        isDone = true;
    }

    public String GetDescription() {
        return this.description;
    }

    public void RemoveDone() {
        isDone = false;
    }

    public String FullStatusIcon() {
        return "[" + GetStatusIcon() + "]";
    }

    @Override
    public String toString() {
        return FullStatusIcon() + " " + description;
    }
}