package ip.exception;

/**
 * Exception thrown on encountering deadline issues.
 */
public class BadDeadline extends DukeException {
    private final String deadline;

    public BadDeadline(String s) {
        deadline = s;
    }

    @Override
    public String toString() {
        if (deadline.isEmpty()) {
            return "Deadline not specified for event!\n"
                    + "Format: deadling {description} /by {d/m/yyyy HH:mm}\n"
                    + "Correct example: `deadline Homework /by 2/11/2022 2359`";
        } else {
            return "Deadline specified: \"" + deadline + "\" is formatted incorrectly.\n"
                    + "Format: deadling {description} /by {d/m/yyyy HH:mm}\n"
                    + "Correct example: `deadline Homework /by 2/11/2022 2359`";
        }
    }
}
