package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a more specific task with time limit attached to it. A <code>Deadline</code> object corresponds to
 * a task by a specific time e.g., <code>02-12-2018 18:00</code>
 */
public class Deadline extends Task {
    private String dateTime;
    private LocalDateTime date;

    /**
     * Constructor for Deadline.
     *
     * @param name      Name of the Task.
     * @param completed The status of the Task. (Completed or not)
     * @param dateTime  The deadline.
     */
    public Deadline(String name, boolean completed, String dateTime) {
        super(name, completed);
        this.dateTime = dateTime;
        applyDate(dateTime);
    }

    /**
     * Returns the deadline.
     *
     * @return Time of deadline.
     */
    @Override
    public String getTime() {
        return dateTime;
    }

    /**
     * Returns The type of task.
     *
     * @return Type of task.
     */
    @Override
    public String getTaskType() {
        return "D";

    }

    /**
     * Applies date for the constructor.
     *
     * @param dateTime String type time of deadline. ("20-04-1990 19:30")
     */
    private void applyDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.date = LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Returns date and time.
     *
     * @return Date and time in MMM d yyyy HH:mm e.g. "Jan 9 1998 19:30".
     */
    public String getDateTime() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Returns date and time with a specific format.
     *
     * @param format String of a format e.g. "yyyy-dd-MM HH:mm".
     * @return The date and time in a specific format.
     */
    public String getDateTime(String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Returns the date only in MMM d yyyy format.
     *
     * @return Date in MMM d yyyy format.
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Toggles the completion of deadline.
     *
     * @return A toggled version of deadline. (Completed = true -> Completed = false)
     */
    @Override
    public Task toggleCompleted() {
        return new Deadline(getName(), !isCompleted(), dateTime);

    }

    /**
     * Checks the validity of the date and time provided in a string.
     *
     * @param date Date and time in String.
     * @throws Exception If improper date is entered.
     */
    public static void validDateTime(String date) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime d = LocalDateTime.parse(date, formatter);
    }

    /**
     * Converts deadline to a string.
     *
     * @return A string that represent a deadline. E.g. [D][X] get a book /by 20-04-1990 19:30.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", checkMarked(), getName(), getDateTime());
    }

}
