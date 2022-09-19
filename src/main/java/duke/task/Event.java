package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a specific date/time it happens at.
 * @author Aaron Pang
 * @version CS2103T AY22/23 Semester 1
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructs a duke.task.Event task without isDone.
     *
     * @param description Description of the duke.task.Event task.
     * @param at Date the duke.task.Event task occurs at.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs a duke.task.Event task without isDone.
     *
     * @param description Description of the duke.task.Event task.
     * @param isDone True if task is done, false otherwise.
     * @param at Date the duke.task.Event task occurs at.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    public String formatTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Shows the duke.task.Event task description and the date it occurs at.
     *
     * @return String with the duke.task.Event task description and the date it occurs at.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatTime(at) + ")";
    }

    @Override
    public String saveTask() {
        return String.format("E | %s | %s", super.saveTask(), at);
    }
}
