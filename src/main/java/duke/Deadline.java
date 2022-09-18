package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task implements Snoozable{
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    public void snooze(){
        this.by = this.by.plusDays(1);
    }

    /**
     * Encodes the task to string for storage.
     * @return the encoded string
     */
    @Override
    public String encodeToString() {
        return "D|" + this.getStatusIcon() + "|" + this.description + "|" + this.by;
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}
