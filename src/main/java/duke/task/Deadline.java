package duke.task;

import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {

    private final LocalDateTime byDate;

    /**
     * Constructor for a new Deadline from stored tasks.
     * @param taskName name of task.
     * @param byDate due date and time of task in the format (dd/mm/yyyy hh:mm).
     * @param isDone to set marked tasks (for storage retrieval).
     */
    public Deadline(String taskName, String byDate, boolean isDone) {
        super(taskName.trim(), isDone);
        this.byDate = Parser.parseDate(byDate);
    }

    /**
     * Constructor for a new Deadline.
     * @param taskName name of task.
     * @param byDate due date and time of task in the format (dd/mm/yyyy hh:mm).
     */
    public Deadline(String taskName, String byDate) {
        super(taskName.trim(), false);
        this.byDate = Parser.parseDate(byDate);
    }

    /**
     * Converts task into a string for storage.
     * @return string to be saved in storage.
     */
    @Override
    public String taskToFileString() {
        return " D " + "| " + (this.done ? "1 " : "0 ") + "| " + this.taskName + " | "
                + dateTimeToString(byDate);
    }

    /**
     * Converts task into string for printing.
     * @return string to be printed to user.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String stringBy = byDate.format(formatter);
        return "[D]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (by: " + stringBy + ")";
    }
}
