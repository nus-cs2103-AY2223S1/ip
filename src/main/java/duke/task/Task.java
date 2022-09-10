package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        if (isDone) {
            System.out.println("This task is already marked as done!");
        } else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n" + this);
        }
    }

    public void markAsDoneWithoutMessage() {
        this.isDone = true;
    }

    public void markAsUndone() {
        if (!isDone) {
            System.out.println("This task is already marked as not done yet!");
        } else {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n" + this);
        }
    }

    public abstract String fileFormat();

    public boolean doesContain(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
