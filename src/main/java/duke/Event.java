package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime occursAt;

    public Event(String taskName, String occursAt, boolean isDone) {
        super(taskName.trim(), isDone);
        this.occursAt = Parser.dateParser(occursAt);
    }

    public Event(String taskName, String occursAt) {
        super(taskName.trim(), false);
        this.occursAt = Parser.dateParser(occursAt);
    }

    @Override
    public String taskToFileString() {
        return " E " + "| " + (this.done ? "1 " : "0 ") + "| " + this.taskName + " | " + Parser.dateTimeToString(occursAt);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String stringAt = occursAt.format(formatter);
        return "[E]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (at: " + stringAt + ")";
    }

}
