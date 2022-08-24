import java.time.LocalDate;

public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) throws EmptyDescException {
        if (desc.equals("")) {
            throw new EmptyDescException("task");
        }
        this.desc = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");    // mark done task
    }

    public String getDesc() {
        return desc;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + desc;
    }
}
