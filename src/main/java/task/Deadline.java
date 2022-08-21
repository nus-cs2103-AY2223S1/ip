package task;

import parser.DateTimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadlineDateTime;

    public Deadline(String description, boolean isDone, LocalDateTime deadlineDateTime) {
        super(description, isDone);
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.changeDateTimeFormat(deadlineDateTime)+ ")";
    }
}