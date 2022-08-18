public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return this.done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    public String toString() {
        if (this.isDone()) {
            return("[X]" + this.description);
        }
        else {
            return("[ ]" + this.description);
        }
    }
}
