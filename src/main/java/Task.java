public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        /** Determines if there is an X icon to show completed */
        String doneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", doneIcon, description);
    }
}
