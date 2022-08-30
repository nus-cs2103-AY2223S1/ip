package ip.exception;

/**
 * Exception thrown on encountering deadline issues.
 */
public class BadDeadline extends DukeException {
    private String deadline;

    public BadDeadline(String s) {
        deadline = s;
    }

    @Override
    public String toString() {
        if (deadline.isEmpty()) {
            return "Deadline not specified for event!\n"
                   + "Correct example: `deadline Homework /by 2/5/2022 2359`";
        } else {
            return "Deadline specified: \"" + deadline + "\" has incorrect formatting.\n"
                   + "Correct example: `deadline Homework /by 2/5/2022 2359`";
        }
    }
}
