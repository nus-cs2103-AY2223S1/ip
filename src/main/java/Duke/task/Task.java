package Duke.task;

/**
 * Represents the object that the chat-bot manages
 * and users can interact with.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(String task, boolean done) {
        this.description = task;
        this.isDone = done;
    }

    /**
     * Changes boolean value done of a task to be true
     * when a user completes it
     */
    public void mark() {
        this.isDone = true;
    }


    /**
     * Changes boolean value done of a task to false
     * when a user marks it as incomplete
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    public String getDone() {
        return ( isDone ? "1" : "0");
    }
    public String printDone() {
        return (isDone ? "[X] " : "[ ] ");
    }
    @Override
    public String toString() {
        return (this.printDone() + this.getDescription());
    }

    public abstract boolean isTodo();
    public abstract boolean isDeadline();
    public abstract boolean isEvent();

}
