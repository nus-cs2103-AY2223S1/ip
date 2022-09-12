package duke.exceptions;

/**
 * Represents an exception due to a wrong format.
 */
public abstract class WrongFormatException extends DukeException {
    private static final String MESSAGE_FORMAT = "Whoops, I didn't quite understand that!\n"
            + "Maybe try this format:\n"
            + "  '%s'.";

    /**
     * Constructs a wrong format exception with a message.
     *
     * @param correctSyntax Correct syntax for the Wrong Format Exception.
     */
    public WrongFormatException(String correctSyntax) {
        super(String.format(MESSAGE_FORMAT, correctSyntax));
    }
}
