package Duke.task;

public abstract class Task {
    protected String description;
    protected boolean done;

    protected Task(String task, boolean done) {
        this.description = task;
        this.done = done;
    }
    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }
    public String getDescription() {
        return this.description;
    }
    public String getDone() {
        return ( done ? "1" : "0");
    }
    public String printDone() {
        return (done ? "[X] " : "[ ] ");
    }
    @Override
    public String toString() {
        return (this.printDone() + this.getDescription());
    }

    public abstract boolean isTodo();
    public abstract boolean isDeadline();
    public abstract boolean isEvent();

}
