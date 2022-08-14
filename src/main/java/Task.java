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

    public void changeIsDone(boolean newIsDone) throws IllegalStateException{
        if (this.isDone == newIsDone) {
            throw new IllegalStateException();
        } else {
            this.isDone = newIsDone;
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
