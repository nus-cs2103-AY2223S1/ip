package task;

import exception.EventException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Event extends Task {
    private LocalDate time;

    public Event(String description, String time) throws EventException {
        super(description);
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException error) {
            throw new EventException("The time given is not a valid date. " +
                    "Try to represent the time in yyyy-mm-dd format.");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toStorageRepresentation() {
        return "E|" + super.toStorageRepresentation() + "|" + this.time;
    }
}
