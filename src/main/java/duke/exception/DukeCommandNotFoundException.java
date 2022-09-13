package duke.exception;

/**
 * Throws an exception when an invalid command in given.
 */
public class DukeCommandNotFoundException extends DukeException {
    public static final String EXCEPTION_DESCRIPTION = "Could you rephrase what you said?";
    /**
     * Throws an error message when command is not understood.
     */
    public DukeCommandNotFoundException() {
        super(EXCEPTION_DESCRIPTION);
    }
}
