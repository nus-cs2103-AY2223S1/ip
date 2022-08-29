package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is happening at a date, and its duration.
 */
public class Event extends Task {
    protected LocalDate at;
    protected LocalTime timeStart;
    protected LocalTime timeEnd;

    /**
     * Creates an event object upon receiving an event command from the user.
     *
     * @param description The description of the event.
     * @param at The date of the event.
     * @param timeStart The starting time of the event.
     * @param timeEnd The ending time of the event.
     * @param type The type of task created.
     */
    public Event(String description, LocalDate at, LocalTime timeStart, LocalTime timeEnd, TaskType type) {
        super(description, type);
        this.at = at;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Returns the description of an event with its date and duration for writing purposes.
     *
     * @return The string containing the description.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " | " + at + " " + timeStart + "-" + timeEnd;
    }
    @Override
    public boolean isDateEqual(LocalDate date) {
        return at.isEqual(date);
    }

    @Override
    public String toString() {
        String str = "";
        str += timeStart.format(DateTimeFormatter.ofPattern("HH:mm ")) + "to ";
        str += timeEnd.format(DateTimeFormatter.ofPattern("HH:mm "));
        str += at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + "(at: " + str + ")";
    }
}
