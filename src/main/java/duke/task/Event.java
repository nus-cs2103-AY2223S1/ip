package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task
 */
public class Event extends Task {
    private final LocalDateTime atDate;

    /**
     * Constructor for a new Event from stored tasks.
     * @param taskName name of task.
     * @param atDate date and time of task in the format (dd/mm/yyyy hh:mm).
     * @param isDone to set marked tasks (for storage retrieval).
     */
    public Event(String taskName, String atDate, boolean isDone) {
        super(taskName.trim(), isDone);
        this.atDate = Parser.parseDate(atDate);
    }

    /**
     * Constructor for a new Event.
     * @param taskName name of task.
     * @param atDate date and time of task in the format (dd/mm/yyyy hh:mm).
     */
    public Event(String taskName, String atDate) {
        super(taskName.trim(), false);
        this.atDate = Parser.parseDate(atDate);
    }

    /**
     * Converts task into a string for storage.
     * @return string to be saved in storage.
     */
    @Override
    public String taskToFileString() {
        return " E " + "| " + (this.done ? "1 " : "0 ") + "| " + this.taskName + " | "
                + dateTimeToString(atDate);
    }

    /**
     * Converts task into string for printing.
     * @return string to be printed to user.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String stringAt = atDate.format(formatter);
        return "[E]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (at: " + stringAt + ")";
    }

}
