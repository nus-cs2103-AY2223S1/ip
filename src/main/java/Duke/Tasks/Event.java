package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that extends from Task with additional variable LocalDataTime
 * and represents as Event task.
 */
public class Event extends Task {
    protected LocalDateTime eventTime;

    /**
     * Public Constructor of Event class.
     * @param description The description of the Event task.
     * @param eventDate The date of event happening.
     * @param eventTime The time of event happening.
     * @param isDone The status of event class.
     */
    public Event (String description, LocalDate eventDate,
                  LocalTime eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = LocalDateTime.of(eventDate, eventTime);
    }

    /**
     * Public Constructor of Event class.
     * @param description The description of the Event task.
     * @param eventTime The date and time of event happening.
     * @param isDone The status of event class.
     */
    public Event (String description, LocalDateTime eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = eventTime;
    }
    @Override
    public String toString() {
        return String.format("[E]%s (%s)", super.toString(),
                this.eventTime.format(DateTimeFormatter
                        .ofPattern("MMM d yyyy HH:mm")));
    }
    @Override
    public String save() {
        return String.format("E | %b | %s | %s\n",
                super.getIsDone(),
                this.description,
                this.eventTime.toString());
    }
    @Override
    public String getTaskType() { return "Event"; }
    @Override
    public LocalDateTime getDateTime() {
        return this.eventTime;
    }
}
