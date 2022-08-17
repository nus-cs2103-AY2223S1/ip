public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void mark() {
        done = true;
    }
    
    public void unmark() {
        done = false;
    }

    private String check() {
        return done ? "[X] " : "[ ] ";
    }

    @Override
    public String toString() {
        return check() + description;
    }
}
