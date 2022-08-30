package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start date and time, as well as an end date and time.
 */
public class Event extends Task {
    private LocalDate dateStart;
    private LocalTime timeStart;

    private LocalDate dateEnd;
    private LocalTime timeEnd;

    /**
     * Initializes a new Event object which is a subclass of Task.
     *
     * @param description Description of the Event task.
     * @param dateStart Start date of the Event task.
     * @param timeStart Start time of the Event task.
     * @param dateEnd End date of the Event task.
     * @param timeEnd End time of the Event task.
     * @throws DateTimeParseException If the inputted time is invalid and cannot be parsed.
     */
    public Event(String description,
                    String dateStart, String timeStart,
                    String dateEnd, String timeEnd) throws DateTimeParseException {
        super(description);
        this.dateStart = LocalDate.parse(dateStart);
        this.timeStart = LocalTime.parse(timeStart);
        this.dateEnd = LocalDate.parse(dateEnd);
        this.timeEnd = LocalTime.parse(timeEnd);
    }

    /**
     * Initializes a new Event object which is a subclass of Task, with the option to specify if the
     * task is done or not.
     *
     * @param description Description of the Event task.
     * @param status Completion state of the Event task.
     * @param dateStart Start date of the Event task.
     * @param timeStart Start time of the Event task.
     * @param dateEnd End date of the Event task.
     * @param timeEnd End time of the Event task.
     * @throws DateTimeParseException If the inputted time is invalid and cannot be parsed.
     */
    public Event(String description, int status,
                 String dateStart, String timeStart,
                 String dateEnd, String timeEnd) throws DateTimeParseException {
        this(description, dateStart, timeStart, dateEnd, timeEnd);
        isDone = status == 1;
    }

    private String formatAsMmmDdYyyy(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String formatAs12Hour(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String parseToSaveData() {
        return "E" + "|" + super.parseToSaveData() + "|"
                + dateStart + "|" + timeStart + "|" + dateEnd + "|" + timeEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + formatAsMmmDdYyyy(dateStart) + " " + formatAs12Hour(timeStart) + " - "
                + formatAsMmmDdYyyy(dateEnd) + " " + formatAs12Hour(timeEnd) + ")";
    }
}
