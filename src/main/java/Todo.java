public class Todo extends Task {
    protected String description;
    protected boolean isDone;

    public Todo(String description) {
        this.description = description;
    }

    public void isMark(boolean bool) {
        this.isDone = bool;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " +
                this.description;
    }
}
