package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event that can be described and marked as done,
 * and holds a date on which the task will be held.
 */
public class Event extends Task {
    private LocalDateTime atDate;

    public Event(String description, LocalDateTime atDate) {
        super(description);
        assert atDate != null: "Date cannot be null";
        this.atDate = atDate;
    }

    /**
     * Returns a string that is safe to use with the save file.
     *
     * @return String that is of the save file format.
     */
    @Override
    public String saveText() {
        return String.format(
                "%d event %s /at %s",
                this.isDone ? 1 : 0,
                this.description,
                this.atDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy H:mm"))
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }
}
