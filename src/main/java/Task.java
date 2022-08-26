public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String checkBox = isDone ? "X" : " ";
        return "[" + checkBox + "] " + description;
    }

    public String toDbString() {
        String binaryIsDone = isDone ? "1" : "0";
        return binaryIsDone + " | " + description;
    }
}
