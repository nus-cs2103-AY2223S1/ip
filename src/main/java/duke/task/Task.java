package duke.task;

public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    public String getSaveFormat() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public int compareTo(Task other) {
        if (isDone != other.isDone) {
            return Boolean.compare(isDone, other.isDone);
        }
        return description.compareTo(other.description);
    }
}
