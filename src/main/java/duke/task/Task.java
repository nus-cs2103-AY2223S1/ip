package duke.task;

public abstract class Task {
    private boolean isDone;
    private String text;

    public Task(boolean isDone, String text) {
        this.isDone = isDone;
        this.text = text;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean textContains(String s) {
        return this.text.contains(s);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', this.text);
    }
}
