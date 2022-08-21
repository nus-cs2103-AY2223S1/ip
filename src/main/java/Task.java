public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public Task(String description, String done, String type) {
        this.description = description;
        this.type = type;

        if (done.equals("1")) {
            this.isDone = true;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getTaskType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDateline() {
        return "";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
