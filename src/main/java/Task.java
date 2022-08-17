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

    String getDescription() {
        return this.description;
    }

    String getStatusIcon() {
        return isComplete ? "[X]" : "[ ]";
    }

    int getStatusNumber() {
        return isComplete ? 1 : 0;
    }

    String toStorageFormat() {
        return this.getStatusNumber() + " | " + this.description;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), description);
    }
}
