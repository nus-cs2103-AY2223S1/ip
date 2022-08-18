public class Task {
    private String title;
    private boolean done;

    public Task(String title, boolean done) {
        this.title = title;
        this.done = done;
    }

    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return title;
    }
}
