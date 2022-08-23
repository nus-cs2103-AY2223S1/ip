package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.EventException;

public class Event extends Task {
    private final String timeString;
    private final LocalDate time;

    public Event(String description, String timeString) throws EventException {
        super(description);

        try {
            this.timeString = timeString;
            this.time = LocalDate.parse(this.timeString);
        } catch (DateTimeParseException error) {
            throw new EventException("The time given is not a valid date. "
                    + "Try to represent the time in yyyy-mm-dd format.");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toStorageRepresentation() {
        return "E|" + super.toStorageRepresentation() + "|" + this.timeString;
    }
}
