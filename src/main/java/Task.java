public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDoneStatus() {
        return (this.isDone ? "X" : " ");
    }

    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
