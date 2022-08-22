public class Task {

    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    protected String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    protected void mark() {
        isDone = true;
    }

    protected void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return getStatus() + " " + task;
    }

}
