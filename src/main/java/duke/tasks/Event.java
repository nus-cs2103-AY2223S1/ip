package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates an event item
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructs a new Event
     * @param description The description of the event
     * @param at When the event is taking place
     */
    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(at, formatter);
        this.at = localDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date = this.at.format(formatter);
        return "[E]" + super.toString() + String.format(" (at: %s)", date);
    }

    /**
     * Gets the date of the task
     * @return The date of the task
     */
    @Override
    public LocalDate getDate() {
        return this.at;
    }

    /**
     * Gets the task type
     * @return The task type
     */
    @Override
    public String getTaskType() {
        return "E";
    }
}
