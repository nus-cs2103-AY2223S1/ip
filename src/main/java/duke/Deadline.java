package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task with a date and time.
 */
public class Deadline extends Task {

    private String by;
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for a deadline task.
     *
     * @param description The description of the deadline task.
     * @param by The time of deadline in a long String.
     * @throws DateTimeException If missing a date or time.
     */
    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        this.by = by;
        String[] split = by.split(" ");
        if (split.length == 1) {
            throw new DateTimeException("Missing time/date");
        }
        String dateString = split[0];
        String timeString = split[1];
        this.date = LocalDate.parse(dateString);
        this.time = LocalTime.parse(timeString);
    }

    @Override
    public String toSaveData() {
        return "D" + " | " + super.toSaveData() + " | " + by;
    }

    @Override
    public String toString() {
        String dateMessage = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String timeMessage = time.format(DateTimeFormatter.ofPattern("HH:mm"));

        return "[D]" + super.toString() + " (by: " + dateMessage + " " + timeMessage + ")";
    }
}
