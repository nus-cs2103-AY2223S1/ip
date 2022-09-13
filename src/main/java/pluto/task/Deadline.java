package pluto.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task.
 */
public class Deadline extends Task {
    /** Deadline time */
    private LocalDateTime by;

    /**
     * Initializes global variables.
     * @param description Deadline description.
     * @param by Task deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String color = (isDone ? Task.ANSI_GREEN : Task.ANSI_RED);
        return color + "[D]" + super.toString() + " (by: " + getDateTime(by) + ")" + Task.ANSI_RESET;
    }

    @Override
    public String toFile() {
        int done = (isDone ? 1 : 0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return String.format("D | %d | %s | %s", done, description, dtf.format(by));
    }

    @Override
    public LocalDate getDateMaybe() {
        return by.toLocalDate();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline other = (Deadline) o;
            return this.by.equals(other.by) && this.description.equals(other.description)
                    && this.isDone == other.isDone;
        }
        return false;
    }
}
