package duke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public  Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
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

    @Override
    public String toString() {
        String s = "[%s] %s";
        return String.format(s, getStatusIcon(), description);
    }


    public String toFile() {
        String s = "%s,%s";
        return String.format(s, getStatusIcon(), description);
    }
}
