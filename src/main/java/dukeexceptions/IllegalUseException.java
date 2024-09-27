package dukeexceptions;

/**
 * Thrown when a function in a parent class that shouldn't be directly called and instead be
 * called from a child class is called.
 */
public class IllegalUseException extends DukeException {
    /**
     * Constructor for IllegalUseException.
     *
     * @param s String for the function's name.
     */
    public IllegalUseException(String s) {
        super(String.format("%s should not be used and should be overridden!", s));
    }
}
