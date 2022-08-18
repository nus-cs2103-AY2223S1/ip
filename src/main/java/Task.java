public class Task {

    private boolean isDone;
    private String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.printf("Nice! I've marked this task as done:\n  %s\n", this);
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.printf("OK, I've marked this task as not done yet:\n  %s\n", this);
    }
}
