package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public boolean matchKeyword(CharSequence keyword) {
        return this.description.contains(keyword);
    }

    public abstract String toStringSaveFormat();
    @Override
    public String toString() {
        return String.format("[%s] %s\n", this.isDone ? "X" : " ", this.description);
    }
}
