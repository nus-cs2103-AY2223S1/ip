public class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    @Override
    public String toString() {
        return task;
    }
}
