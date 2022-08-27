package skylark.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import skylark.main.SkylarkException;

/** Represents a Deadline Task. */
public class Deadline extends Task {
    /**
     * Symbol that represents a Deadline task.
     */
    private static final char SYMBOL_DEADLINE = 'D';

    /**
     * Input format required when parsing input from the user.
     */
    private final String inputFormat = "yyyy-MM-dd HHmm"; // 2019-10-15 1800

    /**
     * Date-time object that represents the end date of the task.
     */
    private final LocalDateTime endDate;

    /**
     * Returns a Deadline object.
     * Throws a SkylarkException if the endDate is not parsable.
     *
     * @param description Description of the Task
     * @param endDate     Date in yyyy-MM-dd HHmm format
     * @throws SkylarkException If date is not parsable
     */
    public Deadline(String description, String endDate) throws SkylarkException {
        super(description);
        try {
            this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern(inputFormat));
        } catch (DateTimeParseException dateTimeParseException) {
            throw new SkylarkException("Cannot parse date");
        }
    }

    /**
     * {@inheritDoc}
     * <br><br>
     *
     * Returns the String representation of a Deadline task.
     */
    @Override
    public String toString() {
        // Oct 15 2019
        String outputFormat = "MMM dd yyyy";
        return String.format("[%c] %s (by: %s)",
                Deadline.SYMBOL_DEADLINE, super.toString(),
                this.endDate.format(DateTimeFormatter.ofPattern(outputFormat)));
    }

    /**
     * {@inheritDoc}
     * <br><br>
     *
     * Returns the String representation of a Deadline task that can be written to a file.
     */
    @Override
    public String toStringFile() {
        return String.format("%c | %s | %s", Deadline.SYMBOL_DEADLINE,
                super.toStringFile(),
                this.endDate.format(DateTimeFormatter.ofPattern(inputFormat)));
    }
}
