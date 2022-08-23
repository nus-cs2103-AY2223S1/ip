package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    public static String EVENT_DATETIME_FORMAT = "dd/MM/yyyy HH:mm";
    public static String DISPLAY_DATETIME_FORMAT = "MMM dd yyyy";

    /**
     * Constructs a new Deadline task.
     *
     * @param description description of the task
     * @param by          the deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Factory method to create a Deadline task from an encoded string.
     *
     * @param encodedInput the encoded entry with format deadline|description
     * @param completed    the completion status of the Deadline task
     * @return the Deadline task
     */
    public static Deadline decode(String encodedInput, boolean completed) throws DukeException {
        String[] entries = encodedInput.split("\\|", 2);
        LocalDateTime datetime = LocalDateTime.parse(entries[0], DateTimeFormatter.ISO_DATE_TIME);
        Deadline deadline = new Deadline(entries[1], datetime);
        deadline.setDone(completed);
        return deadline;
    }

    @Override
    public Type getType() {
        return Type.DEADLINE;
    }

    @Override
    public String encodeData() {
        return String.format("%s|%s", by.toString(), this.description);
    }

    @Override
    public String getDisplayText() {
        return String.format("%s (by: %s)", description, by.format(DateTimeFormatter.ofPattern(DISPLAY_DATETIME_FORMAT)));
    }
}
