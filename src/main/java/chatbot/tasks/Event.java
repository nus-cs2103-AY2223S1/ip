package chatbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a subclass of Task emulating
 * an event in a real life where there is a date that is
 * tied to the event.
 */
public class Event extends Task {
    public static final String TYPE = "event";

    private LocalDate date;

    /**
     * The constructor for an Event.
     *
     * @param taskName Name of the event.
     * @param date Date of the event.
     */
    public Event(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String save() {
        return "E | " + this.getStatus() + " | " + this.getTaskName() + " | " + this.date;
    }
}
