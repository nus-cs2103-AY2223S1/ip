public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}
