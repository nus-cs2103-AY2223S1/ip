package duke;

public abstract class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() +"] " + this.name;
    }

    public String getStatusIcon() {
        return (this.done ? "X" : " ");
    }

    public boolean isDone() {
        return this.done;
    }

    public String getName() {
        return this.name;
    }

}
