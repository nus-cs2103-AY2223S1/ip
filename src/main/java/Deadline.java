/**
 * Deadline is a Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    public final static DukeException emptyDescription = new DukeException("Description of Deadline cannot be empty!");
    public final static DukeException wrongFormat =
        new DukeException("Wrong format for Deadline!\n    Should be 'deadline <description> /by <deadline>'.");

    /** Deadline of the deadline. */
    private final String deadline;

    /**
     * Private constructor for a deadline, with a description and deadline.
     * Deadline is set as "not done" when created.
     *
     * @param description Description of a deadline.
     * @param deadline    Deadline of a deadline.
     */
    private Deadline(String description, String deadline) {
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
        return new Deadline(description, deadline);
    }

    /**
     * Factory method for a Deadline, with done and description and date.
     *
     * @param done Whether the Deadline is done.
     * @param description Description of Deadline.
     * @param date Date of Deadline.
     *
     * @return Deadline object with the given parameters.
     */
    public static Deadline create(String done, String description, String date) {
        Deadline deadline = new Deadline(description, date);
        if (done.equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Gets the string representation of a deadline.
     *
     * @return String representation of a deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
