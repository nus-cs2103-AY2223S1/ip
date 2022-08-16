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

    public boolean canChangeIsDone(boolean newIsDone) {
        if (this.isDone == newIsDone) {
            return false;
        } else {
            return true;
        }
    }
    public void changeIsDone(boolean newIsDone){
        this.isDone = newIsDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
