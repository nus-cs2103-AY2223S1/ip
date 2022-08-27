package task;

public class Task {
    protected String description;

    protected String splitDesscription[];
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.splitDesscription = description.split(" ");
    }

    public Boolean hasWord(String word) {
        for (String str : splitDesscription) {
            if (str.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void unMark() {
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "  + description;
    }
}
