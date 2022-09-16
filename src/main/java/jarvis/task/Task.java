package jarvis.task;

public class Task {
    public String description;
    public boolean isDone;
    public static int count = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;

    }

    public void mark(){
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}