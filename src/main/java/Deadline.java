import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline is a Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    public final static DukeException emptyDescription = new DukeException("Description of Deadline cannot be empty!");
    public final static DukeException wrongFormat =
        new DukeException("Wrong format for Deadline!\n    Should be 'deadline <description> /by YYYY-MM-DD'.");

    /** Deadline of the deadline. */
    private final LocalDate deadline;

    /**
     * Private constructor for a deadline, with a description and deadline.
     * Deadline is set as "not done" when created.
     *
     * @param description Description of a deadline.
     * @param deadline    Deadline of a deadline.
     */
    private Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Factory Method for a Deadline, with a user input.
     * Deadline is set as "not done" when created.
     *
     * @param input User input.
     *
     * @return Deadline object with the given user input.
     */
    public static Deadline create(String input) throws DukeException {
        if (input.length() < 1) {
            throw Deadline.emptyDescription;
        }
        // Split the input into description and date with the separator of "/by".
        String[] split = input.split(" /by ");
        if (split.length != 2) {
            throw wrongFormat;
        }
        String description = split[0];
        String deadline = split[1];
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw DukeException.invalidDate;
        }
        return new Deadline(description, localDate);
    }

    /**
     * Gets the string representation of a deadline.
     *
     * @return String representation of a deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy");
        String deadline = this.deadline.format(formatter);
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
