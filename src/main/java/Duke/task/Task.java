package Duke.task;

/**
 * Represents the object that the chat-bot manages
 * and users can interact with.
 */
public abstract class Task {
    protected String description;
    protected boolean done;

    protected Task(String task, boolean done) {
        this.description = task;
        this.done = done;
    }

    /**
     * Changes boolean value done of a task to be true
     * when a user completes it
     */
    public void mark() {
        this.done = true;
    }


    /**
     * Changes boolean value done of a task to false
     * when a user marks it as incomplete
     */
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
