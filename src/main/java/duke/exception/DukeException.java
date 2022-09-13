package duke.exception;

/**
 * Throws an exception when duke requirements is not statisfied.
 */
public abstract class DukeException extends Exception {
    public static final String EXCEPTION_KEYWORD = "YOU SHOULD NOT BE MAKING ANY MISTAKE!\n";
    protected DukeException(String message) {
        super(EXCEPTION_KEYWORD + message);
    }
}
