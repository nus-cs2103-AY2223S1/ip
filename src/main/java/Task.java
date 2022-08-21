public class Task {
    private Boolean isDone;
    private final String description;

    public Task(String description) {
        this.description = description;
        this.markAsNotDone();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getStatus() {
        return this.isDone;
    }

    public String printTask() {
        return String.format("[%s] %s",
                this.getStatus() ? "X" : " ",
                this.getDescription());
    }
}
