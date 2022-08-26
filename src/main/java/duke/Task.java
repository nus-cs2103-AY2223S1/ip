package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String code;

    public Task(String description, String code) {
        this.description = description;
        this.code = code;
        setIsDone(false);
    }

    public Task(String description, String code, boolean isDone) {
        this.description = description;
        this.code = code;
        setIsDone(isDone);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public String getCode() {
        return this.code;
    }

    public void setIsDone(boolean value) {
        this.isDone = value;
    }

    public void markAsDone() {
        setIsDone(true);
    }

    public void unmarkAsDone() {
        setIsDone(false);
    }

    public String printText() {
        return this.getCode() + " | " + this.getStatusIcon() + " | " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}