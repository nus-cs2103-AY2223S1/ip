public abstract class Task {
    String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String toFileRepresentation() {
        return String.format("%d | %s", this.done ? 1 : 0, this.description);
    }

    public static Task fromFileRepresentation(String rep) {
        return null;
    }

    @Override
    public String toString() {
        char doneFlag = done ? 'X' : ' ';
        return String.format("[%c] %s", doneFlag, description);
    }
}
