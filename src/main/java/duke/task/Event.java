package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that is an Event.
 */
public class Event extends Task {

    protected LocalDate time;

    /**
     * Constructor for Event.
     *
     * @param description Description of the event.
     * @param time Time of the event.
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructor for Event.
     *
     * @param description Description of the event.
     * @param time Time of the event.
     * @param done Status of the task.
     */
    public Event(String description, LocalDate time, boolean done) {
        super(description);
        this.time = time;
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Converts the task into a string representation that can be saved into a file.
     *
     * @return String representation of the task.
     */
    @Override
    public String save() {
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.time.toString();
    }

    /**
     * Returns the time of the task, returning LocalDate.MIN if the task is a ToDo.
     *
     * @return Time of the task.
     */
    @Override
    public LocalDate getTime() {
        return this.time;
    }
}