package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {

    private final LocalDateTime dueBy;

    /**
     * Constructor for a new Deadline from stored tasks.
     * @param taskName name of task.
     * @param dueBy due date and time of task in the format (dd/mm/yyyy hh:mm).
     * @param isDone to set marked tasks (for storage retrieval).
     */
    public Deadline(String taskName, String dueBy, boolean isDone) {
        super(taskName.trim(), isDone);
        this.dueBy = Parser.dateParser(dueBy);
    }

    /**
     * Constructor for a new Deadline.
     * @param taskName name of task.
     * @param dueBy due date and time of task in the format (dd/mm/yyyy hh:mm).
     */
    public Deadline(String taskName, String dueBy) {
        super(taskName.trim(), false);
        this.dueBy = Parser.dateParser(dueBy);
    }

    /**
     * Converts task into a string for storage.
     * @return string to be saved in storage.
     */
    @Override
    public String taskToFileString() {
        return " D " + "| " + (this.done ? "1 " : "0 ") + "| " + this.taskName + " | " + dateTimeToString(dueBy);
    }

    /**
     * Converts task into string for printing.
     * @return string to be printed to user.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String stringBy = dueBy.format(formatter);
        return "[D]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (by: " + stringBy + ")";
    }
}
