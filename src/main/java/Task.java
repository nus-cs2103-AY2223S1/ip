public class Task {
    protected String description;
    protected boolean isDone;
    protected String date;

    public void isMark(boolean bool) {
        this.isDone = bool;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return this.description;
    }
}
