package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event Class that encapsulates details of an Event Task.
 *
 * @author Elgin Lee
 */
public class Event extends Task {
    /** The date of the event. */
    private LocalDate date;

    /** The time of the event. */
    private LocalTime time;

    /**
     * Constructor of Event.
     *
     * @param taskName The name of the Event.
     * @param date The date when the Event will happen.
     * @param time The time when the Event will happen.
     */
    public Event(String taskName, String date, String time) {
        super(taskName);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Constructor of Event.
     *
     * @param taskName The name of the event.
     * @param date The date and time when the Event will happen.
     * @param time The time when the Event will happen.
     * @param isDone True if the event is done, false otherwise.
     */
    public Event(String taskName, String date, String time, boolean isDone) throws DateTimeParseException {
        super(taskName, isDone);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Gets the date of the event.
     *
     * @return The date of the event.
     */
    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    /**
     * String representation of an Event.
     *
     * @return Event string representation.
     */
    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("h:mma"));

        // Basic task representation, mark status and task description (e.g. [X] sleep).
        String basicDescription = super.toString();

        return "[E]" + basicDescription + " (at: " + formattedDate + ", " + formattedTime + ")";
    }
}
