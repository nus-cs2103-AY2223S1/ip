package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a new Deadline task.
     *
     * @param description description of the task
     * @param by          the deadline of the task.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("%s does not match the required dd/MM/yyyy HH:mm format", by);
        }
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
        Deadline deadline = new Deadline(entries[1], entries[0]);
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
        return String.format("%s (by: %s)", description, by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
