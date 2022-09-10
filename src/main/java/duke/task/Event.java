package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An event is a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected LocalDateTime eventStartDatetime;
    protected LocalDateTime eventEndDatetime;

    /**
     * Constructs an event task.
     *
     * @param description Description of the event task.
     * @param eventStartDatetime The start datetime of the event.
     * @param eventEndDatetime The end datetime of the event.
     */
    public Event(String description, LocalDateTime eventStartDatetime, LocalDateTime eventEndDatetime) {
        super(description);
        this.eventStartDatetime = eventStartDatetime;
        this.eventEndDatetime = eventEndDatetime;
    }

    /**
     * Constructs an event task with a specified completion status.
     *
     * @param description Description of the event task.
     * @param eventStartDatetime The start datetime of the event.
     * @param eventEndDatetime The end datetime of the event.
     * @param isCompleted Whether the event task is done or not.
     */
    public Event(String description, LocalDateTime eventStartDatetime,
                 LocalDateTime eventEndDatetime, boolean isCompleted) {
        super(description, isCompleted);
        this.eventStartDatetime = eventStartDatetime;
        this.eventEndDatetime = eventEndDatetime;
    }

    /**
     * Returns a String representation of the start datetime object associated to the
     * event task in EEEE, dd MMM yyyy HH:mm format.
     *
     * @return String representation of the start datetime in EEEE, dd MMM yyyy HH:mm format.
     */
    public String getStartDatetimeString() {
        DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm");
        return eventStartDatetime.format(dayDateTimeFormatter);
    }

    /**
     * Returns a String representation of the end datetime object associated to the
     * event task in EEEE, dd MMM yyyy HH:mm format.
     *
     * @return String representation of the end datetime in EEEE, dd MMM yyyy HH:mm format.
     */
    public String getEndDatetimeString() {
        DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm");
        return eventEndDatetime.format(dayDateTimeFormatter);
    }

    /**
     * Parses the Event into a savable string format, ready to be written to the hard disk.
     *
     * @return Savable string representation of the Deadline Task.
     */
    @Override
    public String toSaveFormat() {
        String savableCompletion = isDone ? "Y" : "N";
        // escape instances of deliminator in task description
        String escapedDescription = description.replace("|", "\\|");
        return String.format("E | %s | %s | %s | %s", savableCompletion, escapedDescription,
                eventStartDatetime, eventEndDatetime);
    }

    /**
     * Returns a string representation for the event task, prefixed with a [E],
     * followed by the event status, and the event description and start to end time formatted
     * in EEEE, dd MMM yyyy HH:mm format.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        String completionDescription = super.toString();
        String formattedStartDateTime = getStartDatetimeString();
        String formattedEndDateTime = getEndDatetimeString();
        return String.format("[E]%s (at: %s to %s)", completionDescription,
                formattedStartDateTime, formattedEndDateTime);
    }
}
