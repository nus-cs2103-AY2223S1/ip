package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task
 */
public class Event extends Task {
    private final LocalDateTime occursAt;

    /**
     * Constructor for a new Event from stored tasks.
     * @param taskName name of task.
     * @param occursAt date and time of task in the format (dd/mm/yyyy hh:mm).
     * @param isDone to set marked tasks (for storage retrieval).
     */
    public Event(String taskName, String occursAt, boolean isDone) {
        super(taskName.trim(), isDone);
        this.occursAt = Parser.dateParser(occursAt);
    }

    /**
     * Constructor for a new Event.
     * @param taskName name of task.
     * @param occursAt date and time of task in the format (dd/mm/yyyy hh:mm).
     */
    public Event(String taskName, String occursAt) {
        super(taskName.trim(), false);
        this.occursAt = Parser.dateParser(occursAt);
    }

    /**
     * Converts task into a string for storage.
     * @return string to be saved in storage.
     */
    @Override
    public String taskToFileString() {
        return " E " + "| " + (this.done ? "1 " : "0 ") + "| " + this.taskName + " | " + dateTimeToString(occursAt);
    }

    /**
     * Converts task into string for printing.
     * @return string to be printed to user.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String stringAt = occursAt.format(formatter);
        return "[E]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (at: " + stringAt + ")";
    }

}
