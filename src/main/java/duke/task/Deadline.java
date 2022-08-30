package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import duke.DukeException;

/**
 * Deadline task with a description and ending date time.
 */
public class Deadline extends Task {
    public static final String EVENT_DATETIME_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String DISPLAY_DATETIME_FORMAT = "MMM dd yyyy";
    protected LocalDateTime by;

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
     * Creates a Deadline task from an encoded string.
     *
     * @param encodedInput the encoded entry with format deadline|description.
     * @param completed    the completion status of the Deadline task.
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
        return String.format("%s (by: %s)",
                description,
                by.format(DateTimeFormatter.ofPattern(DISPLAY_DATETIME_FORMAT)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return by.equals(deadline.by) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(by, description, isDone);
    }
}
