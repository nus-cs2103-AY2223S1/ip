package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline that can be described and marked as done,
 * and holds a date by which the task must be completed.
 */
public class Deadline extends Task {
    private LocalDateTime byDate;

    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        assert byDate != null : "Date cannot be null";
        this.byDate = byDate;
    }

    @Override
    public LocalDateTime getTime() {
        return this.byDate;
    }

    @Override
    public void setTime(LocalDateTime time) {
        this.byDate = time;
    }

    /**
     * Returns a string that is safe to use with the save file.
     *
     * @return String that is of the save file format.
     */
    @Override
    public String saveText() {
        return String.format(
                "%d deadline %s /by %s",
                this.isDone ? 1 : 0,
                this.description,
                this.byDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm"))
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }
}
