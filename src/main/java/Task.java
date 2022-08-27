public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "[X]" : "[ ]");
    }

    public void toMark(boolean x) {
        this.isDone = x;
    }
    public String toSave() {
        if (isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
