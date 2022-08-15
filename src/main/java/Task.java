public class Task {
    private final String taskName;
    private boolean markedAsDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void mark() {
        this.markedAsDone = true;
    }

    public void unmark() {
        this.markedAsDone = false;
    }

    @Override
    public String toString() {
        String ticker = "[ ]";

        if (markedAsDone == true) {
            ticker = "[X]";
        }

        return ticker + " " + this.taskName;
    }
}
