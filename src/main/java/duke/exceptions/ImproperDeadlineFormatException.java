package duke.exceptions;

/**
 * Representation of an exception where user input does not follow
 * the specified format for deadlines.
 */
public class ImproperDeadlineFormatException extends DukeException {

    /**
     * Provide user with the specified format to create
     * a Deadline via Duke.
     */
    public ImproperDeadlineFormatException() {
        super("IMPROPER DEADLINE FORMAT. KINDLY FOLLOW:\n"
                + "deadline task /by YYYY-MM-DD hh:mm\n"
                + "i.e deadline CS2109S PS1"
                + " /at 2000-10-10 23:00");
    }
}
