package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate eventBy;

    public Deadline(String description, String eventBy) {
        super(description);
        this.eventBy = LocalDate.parse(eventBy);
    }

    public Deadline(String description, String eventBy, boolean isDone) {
        super(description, isDone);
        this.eventBy = LocalDate.parse(eventBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToFileFormat() {
        return String.format("deadline | %s | %s | %b", super.description, eventBy, super.isDone);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                eventBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
