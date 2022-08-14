public class Task {
    private boolean completed = false;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void setUncompleted() {
        this.completed = false;
    }

    public String toString() {
        if (completed) {
            return "[X] " + name;
        } else {
            return "[] " + name;
        }
    }
}
