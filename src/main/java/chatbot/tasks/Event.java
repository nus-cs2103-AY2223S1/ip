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
    public Event(String taskName, LocalDate date, String[] tags) {
        super(taskName, tags);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String save() {
        String tagsString = this.saveTags();
        if (tagsString != null) {
            return String.format("E | %s | %s | %s | %s", this.getStatus(), this.getTaskName(), this.date, tagsString);
        }
        return String.format("E | %s | %s | %s", this.getStatus(), this.getTaskName(), this.date);
    }
}
