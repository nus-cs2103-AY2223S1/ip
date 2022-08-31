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
     * @param description Description of the event task
     * @param eventStartDatetime The start datetime of the event
     * @param eventEndDatetime The end datetime of the event
     */
    public Event(String description, LocalDateTime eventStartDatetime, LocalDateTime eventEndDatetime) {
        super(description);
        this.eventStartDatetime = eventStartDatetime;
        this.eventEndDatetime = eventEndDatetime;
    }

    /**
     * Constructs an event task with a specified completion status.
     *
     * @param description Description of the event task
     * @param eventStartDatetime The start datetime of the event
     * @param eventEndDatetime The end datetime of the event
     * @param completion Whether the event task is done or not
     */
    public Event(String description, LocalDateTime eventStartDatetime,
                 LocalDateTime eventEndDatetime, boolean completion) {
        super(description, completion);
        this.eventStartDatetime = eventStartDatetime;
        this.eventEndDatetime = eventEndDatetime;
    }

    /**
     * Parses the Event into a savable string format, ready to be written to the hard disk.
     * @return Savable string representation of the Deadline Task
     */
    @Override
    public String toSaveFormat() {
        return String.format("E | %s | %s | %s | %s", isDone ? "Y" : "N",
                description.replace("|", "\\|"),
                eventStartDatetime, eventEndDatetime);
    }

    /**
     * Returns a string representation for the event task, prefixed with a [E],
     * followed by the event status, and the event description and start to end time.
     *
     * @return The string representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s to %s)", super.toString(),
                eventStartDatetime.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm")),
                eventEndDatetime.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm")));
    }
}
