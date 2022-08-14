public class Task {
    private String taskString;
    private boolean done;

    public Task(String taskString) {
        this.taskString = taskString;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + this.taskString;
        } else {
            return "[ ] " + this.taskString;
        }
    }
}
