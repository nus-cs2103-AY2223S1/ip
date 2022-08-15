public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getTypeIcon() {
        return "";
    }

    public void makeDone() {
        this.isDone = true;
    }

    public void makeUndone() {
        this.isDone = false;
    }

    public String addString(int i) {
        return "";
    }
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.description);
    }
}

