package tasks;

public abstract class Task {
    protected String description;
    protected Boolean isDone;

    public Task(ParsedData parsedData) {
        this.description = parsedData.getTaskName();
        this.isDone = parsedData.getStatus();
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String getStatusLetter() {
        return isDone ? "X" : " ";
    }

    public abstract String getTypeIcon();

    public abstract String getTypeLetter();

    public abstract String getDuring();

    public abstract String getTimeText();

    public String getDescription() {
        return this.description;
    }
}
