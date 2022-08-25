package tasks;

import parser.TimeParser;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeParseException;

public class Event extends Task{

    protected LocalDateTime deadline;
    private static final TimeParser TIME_PARSER = new TimeParser();

    public Event(String description, String deadline) {
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
        return "[D]" + super.toString() + " (at: " + TIME_PARSER.formatDeadline(this.deadline) + ")";
    }
}
