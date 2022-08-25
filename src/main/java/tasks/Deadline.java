package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import parser.TimeParser;

public class Deadline extends Task{

    protected LocalDateTime deadline;
    private static final TimeParser TIME_PARSER = new TimeParser();

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = TIME_PARSER.createDeadline(deadline);
    }

    @Override
    public boolean isBefore(String deadline) throws DateTimeParseException {
        LocalDateTime before = TIME_PARSER.createDeadline(deadline);
        return this.deadline.isBefore(before);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + TIME_PARSER.formatDeadline(this.deadline) + ")";
    }
}
