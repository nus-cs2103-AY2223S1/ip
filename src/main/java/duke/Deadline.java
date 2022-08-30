package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task with an end date and time.
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    /**
     * Initializes a new Deadline object which is a subclass of Task.
     *
     * @param description Description of the Deadline task.
     * @param date Due date of the Deadline task.
     * @param time Due time of the Deadline task.
     * @throws DateTimeParseException If the inputted time is invalid and cannot be parsed.
     */
    public Deadline(String description, String date, String time) throws DateTimeParseException {
        super(description);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Initializes a new Deadline object which is a subclass of Task, with the option to specify if the
     * task is done or not.
     *
     * @param description Description of the deadline task.
     * @param status Completion state of the deadline task.
     * @param date Due date of the deadline task.
     * @param time Due time of the deadline task.
     * @throws DateTimeParseException If the inputted time is invalid and cannot be parsed.
     */
    public Deadline(String description, int status, String date, String time) {
        this(description, date, time);
        isDone = status == 1;
    }

    private String formatAsMmmDdYyyy() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String formatAs12Hour() {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String parseToSaveData() {
        return "D" + "|" + super.parseToSaveData() + "|" + date + "|" + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + formatAsMmmDdYyyy() + " " + formatAs12Hour() + ")";
    }
}
