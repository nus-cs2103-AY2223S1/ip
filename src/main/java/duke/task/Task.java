package duke.task;

public class Task {
    private String task;
    private boolean done;
    public Task(String task) {
        this.task = task;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return this.task;
    }

    @Override
    public String toString() {
        return done ? "[X] " + task : "[ ] " + task;
    }
}
