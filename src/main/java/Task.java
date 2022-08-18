public class Task {
    private String description;
    private boolean done;

    public void describe(String s) {
        this.description = s;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    private String check() {
        return done ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return check() + " " + description;
    }
}
