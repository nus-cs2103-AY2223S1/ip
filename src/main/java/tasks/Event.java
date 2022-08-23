package tasks;

import utils.DeadlineParser;

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.time.format.DateTimeParseException;

public class Event extends Task{

    protected LocalDateTime deadline;
    private static final DeadlineParser DEADLINE_PARSER = new DeadlineParser();

    public Event(String description, String deadline) {
        super(description);
        this.deadline = DEADLINE_PARSER.createDeadline(deadline);
    }

    @Override
    public boolean isBefore(String deadline) throws DateTimeParseException {
        LocalDateTime before = DEADLINE_PARSER.createDeadline(deadline);
        return this.deadline.isBefore(before);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + DEADLINE_PARSER.formatDeadline(this.deadline) + ")";
    }
}
