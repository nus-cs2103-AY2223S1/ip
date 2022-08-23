package duke.task;

import duke.exception.EventException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Event extends Task {
    private String timeString;
    private LocalDate time;

    public Event(String description, String timeString) throws EventException {
        super(description);
        try {
            this.timeString = timeString;
            this.time = LocalDate.parse(this.timeString);
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
        return "E|" + super.toStorageRepresentation() + "|" + this.timeString;
    }

    /**
     * Returns true if the Event takes place on the given date.
     *
     * @param selectedDate the date object.
     * @return true if the Event happens on the selected date, false otherwise.
     */
    @Override
    public boolean isOnGivenDate(LocalDate selectedDate) {
        return this.time.equals(selectedDate);
    }
}
