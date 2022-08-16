public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAs(boolean bool) {
        this.isDone = bool;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
