package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a specific date/time it needs to be done by.
 * @author Aaron Pang
 * @version CS2103T AY22/23 Semester 1
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a duke.task.Deadline task without isDone.
     *
     * @param description Description of the duke.task.Deadline task.
     * @param by Date the duke.task.Deadline task is due by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a duke.task.Deadline task with isDone.
     *
     * @param description Description of the duke.task.Deadline task.
     * @param isDone true if task is done, false otherwise.
     * @param by Date the duke.task.Deadline task is due by.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Formats the time to a string.
     * @param time time of the Deadline.
     * @return String representation of the time.
     */
    public String formatTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Shows the duke.task.Deadline task description and the date it is due by.
     *
     * @return String with the duke.task.Deadline task description and date it is due by.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatTime(by) + ")";
    }

    @Override
    public String saveTask() {
        return String.format("D | %s | %s", super.saveTask(), by);
    }
}
