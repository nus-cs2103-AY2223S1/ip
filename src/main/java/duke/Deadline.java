package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dueBy;

    public Deadline(String taskName, String dueBy, boolean isDone) {
        super(taskName.trim(), isDone);
        this.dueBy = Parser.dateParser(dueBy);
    }

    public Deadline(String taskName, String dueBy) {
        super(taskName.trim(), false);
        this.dueBy = Parser.dateParser(dueBy);
    }

    @Override
    public String taskToFileString() {
        return " D " + "| " + (this.done ? "1 " : "0 ") + "| " + this.taskName + " | " + Parser.dateTimeToString(dueBy);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String stringBy = dueBy.format(formatter);
        return "[D]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (by: " + stringBy + ")";
    }
}
