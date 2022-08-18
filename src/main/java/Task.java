public class Task {
    private String description;
    private boolean done = false;

    public Task(String command) {
        this.description = command;
    }

    public String getDescription() {
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
