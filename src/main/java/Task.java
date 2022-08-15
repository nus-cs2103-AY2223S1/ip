public class Task {
    protected String description;
    protected String modifier;
    protected boolean isDone;
    private final TaskTypes type;

    public Task(String description, String modifier, TaskTypes type) {
        this.description = description;
        this.modifier = modifier;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]"; // mark done task with X
    }

    public void setComplete(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        switch (type) {
            case TODO:
                return "[T]" + getStatusIcon() + description;
            case DEADLINE:
                return "[D]" + getStatusIcon() + " " + description + " (by: " +
                       modifier + ")";
            default:
                return "[E]" + getStatusIcon() + " " + description + " (at: " +
                       modifier + ")";
        }
    }
}
