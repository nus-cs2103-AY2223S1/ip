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

    public void finished() {
        isDone = true;
    }

    public void notFinished() {
        isDone = false;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[" + getStatusIcon() + "] ");
        str.append(description);
        return str.toString();
    }
}
