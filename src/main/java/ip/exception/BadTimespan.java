package ip.exception;

/**
 * Exception thrown on encountering timespan issues.
 */
public class BadTimespan extends DukeException {
    private String timespan;

    public BadTimespan(String s) {
        timespan = s;
    }

    @Override
    public String toString() {
        if (timespan.isEmpty()) {
            return "Timespan not specified for event!\n"
                   + "Correct example: `event Meeting /at Tuesday, 2-3pm`";
        } else {
            return "Timespan specified: \"" + timespan + "\" has incorrect formatting.\n"
                   + "Correct example: `event Meeting /at Tuesday, 2-3pm`";
        }
    }
}
