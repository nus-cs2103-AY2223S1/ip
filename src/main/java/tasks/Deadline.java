package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import utils.DeadlineParser;

public class Deadline extends Task{

    protected LocalDateTime deadline;
    private static final DeadlineParser DEADLINE_PARSER = new DeadlineParser();

    public Deadline(String description, String deadline) {
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
        return "[D]" + super.toString() + " (by: " + DEADLINE_PARSER.formatDeadline(this.deadline) + ")";
    }
}
