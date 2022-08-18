public class Task extends Duke{

    private final String task;
    private boolean completed;

    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public boolean getStatus() {
        return completed;
    }
    public String toString() {
        if (completed) {
            return " [X] " + task;
        } else {
            return " [ ] " + task;
        }
    }
}
