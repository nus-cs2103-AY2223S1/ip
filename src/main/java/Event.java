package anya;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with task description and event date.
 */
class Event extends Task {
    private LocalDate time;
    private String timeInString;

    Event(String description, String date) throws DateTimeParseException {
        super(description);
        this.time = LocalDate.parse(date);
        this.timeInString = date;
    }

    @Override
    public String toString() {
        String timeString = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " at " + timeString;
    }

    public String write() {
        return "E" + super.write() + ":" + this.timeInString;
    }

}
