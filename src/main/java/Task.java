public class Task {
    protected String description;
    protected String modifier;
    protected boolean isDone;
    private final Commands type;

    public Task(String description, String modifier, Commands type) {
        this(description, modifier, type, false);
    }

    public Task(String description, String modifier, Commands type, boolean isDone) {
        this.description = description;
        this.modifier = modifier;
        this.isDone = isDone;
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
            return "[T]" + getStatusIcon() + " " + description;
        case DEADLINE:
            return "[D]" + getStatusIcon() + " " + description + " (by: "
                   + modifier + ")";
        default:
            return "[E]" + getStatusIcon() + " " + description + " (at: "
                   + modifier + ")";
        }
    }

    public String toSimpleString() {
        switch (type) {
        case TODO:
            return "T | " + (isDone ? 1 : 0) + " | " + description;
        case DEADLINE:
            return "D | " + (isDone ? 1 : 0) + " | " + description + " | (by: "
                   + modifier + ")";
        default:
            return "E | " + (isDone ? 1 : 0) + " | " + description + " | (at: "
                   + modifier + ")";
        }
    }
}
