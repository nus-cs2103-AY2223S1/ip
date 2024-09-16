package duke.tasks;

import duke.Parser;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Representing a deadline, i.e. a task with a specified due date
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime localDateTime;
    // Supported DateTime and Date formats
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd[ HHmm]");


    public Deadline(String description, String by) {
        super(description);
        try {
            this.localDateTime = LocalDate.parse(by, df).atTime(0,0,0);
            this.localDateTime = LocalDateTime.parse(by, dtf);
        } catch (DateTimeParseException ignored) {
            // Ignore usage of localDateTime if input is not formatted as one
        }
        this.by = by;
    }

    public String getTime() {
        return by;
    }

    /**
     * Generates an encoding of the Task for use in storage
     * @return encoded string following the storage format
     */
    public String getStorageString() {
        return "D" + "|" + (isDone ? "1" : "0") + "|" + getDescription() + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (localDateTime == null ? by
                    : localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")))
                + ")";
    }
}