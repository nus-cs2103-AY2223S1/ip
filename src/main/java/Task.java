public class Task {
    protected String description;
    protected boolean done = false;

    public Task(String command) {
        this.description = command;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
}
