public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void toggleDoneness() {
        this.done = this.done ^ true;
    }

    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
