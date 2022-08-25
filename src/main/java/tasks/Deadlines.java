package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.deadline = LocalDateTime.parse(deadline.strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy k:mm"));
    }


    public String toString() {
        String result = "[D]" + "[" + getStatusIcon() + "] " + this.description.strip() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
        return result;
    }

    public String fileString() {
        String write = "D / " + (isDone ? "1 / " : "0 / ") + this.description.strip() + " / "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy k:mm"));
        return write;
    }

}
