public class Task {
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
        if (this.done) {
            return "[x] " + name;
        } else {
            return "[ ] " + name;
        }
    }

}
