package bloop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task with a deadline.
 */
public class Deadline extends Task{

    private String by;
    private LocalDateTime dateTime;

    /**
     * Constructor for Deadline object.
     *
     * @param task Task to be performed.
     * @param by Deadline for the task.
     */
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
        this.dateTime = LocalDateTime.parse(by.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));;
    }

    public String getDateTime() {
        return formatDateTime(dateTime);
    }

    public String getBy() {
        return by;
    }

    public char getType() {
        return 'D';
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDateTime() + ")";
    }
}
