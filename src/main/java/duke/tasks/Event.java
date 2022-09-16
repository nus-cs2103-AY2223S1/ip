package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event at a specified time
 */
public class Event extends Task {
    protected String at;
    protected LocalDateTime localDateTime;
    // Supported DateTime and Date formats
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd[ HHmm]");

    public Event(String description, String at) {
        super(description);
        try {
            this.localDateTime = LocalDate.parse(at, df).atTime(0,0,0);
            this.localDateTime = LocalDateTime.parse(at, dtf);
        } catch (DateTimeParseException ignored) {
            // Ignore usage of localDateTime if input is not formatted as one
        }
        this.at = at;
    }

    public String getTime() {
        return at;
    }

    /**
     * Generates an encoding of the Task for use in storage
     * @return encoded string following the storage format
     */
    public String getStorageString() {
        return "E" + "|" + (isDone ? "1" : "0") + "|" + getDescription() + "|" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + (localDateTime == null ? at
                : localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")))
                + ")";
    }
}
