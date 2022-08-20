import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    private final Commands type;
    private final LocalDateTime date;

    public Task(Commands type, String description, LocalDateTime date) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.isDone = false;
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
                   + date.toString().replace("T", " ") + ")";
        default:
            return "[E]" + getStatusIcon() + " " + description + " (at: "
                   + date.toString().replace("T", " ") + ")";
        }
    }
}
