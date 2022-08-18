public abstract class Task {

    private boolean isDone;
    private String description;
    private String taskIcon;

    public Task(String description, String taskIcon) {
        this.description = description;
        this.isDone = false;
        this.taskIcon = taskIcon;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskIcon, this.getStatusIcon(), this.description);
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
