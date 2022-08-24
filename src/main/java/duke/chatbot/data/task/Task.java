package duke.chatbot.data.task;

public abstract class Task {
    private String description;
    private boolean isDone = false;

    public Task(String task) {
        description = task;
    }

    public Task(String task, boolean isDone) {
        this(task);
        this.isDone = isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    public String encode() {
        return ",,," + (isDone ? 1 : 0) + ",,," + description;
    }

    public boolean hasSubstring(String substring) {
        return description.contains(substring);
    }
}
