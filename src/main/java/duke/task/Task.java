package duke.task;

public abstract class Task {
    protected String content;
    protected Boolean status;

    protected Task(String content) {
        this.content = content;
        this.status = false;
    }

    public void markComplete() {
        this.status = true;
    }

    public void unMarkComplete() {
        this.status = false;
    }

    @Override
    public String toString() {
        if (status) {
            return String.format("[x] %s", content);
        } else {
            return String.format("[ ] %s", content);
        }
    }

    public abstract String toFileData();
}
