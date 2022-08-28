package duke.task;

public abstract class Task {
    private boolean done;
    private String name;

    public Task(String name) {
       this.done = false;
       this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public abstract String toMemoryString();

    @Override
    public String toString() {
        return (this.isDone() ? "[X] " : "[ ] ") + this.name;
    }
}
