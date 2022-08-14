public class Task {
    private final String description;
    private boolean isComplete;

    Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    Task(String description) {
        this(description, false);
    }

    void mark() {
        this.isComplete = true;
    }

    void unmark() {
        this.isComplete = false;
    }

    String getStatusIcon() {
        return isComplete ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), description);
    }
}
