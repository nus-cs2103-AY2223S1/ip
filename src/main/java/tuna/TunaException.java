package tuna;

/**
 * Represents Exceptions related to Duke.
 */
public class TunaException extends Exception {

    /**
     * Constructs a DukeException object with the given description.
     *
     * @param description description for the exception.
     */
    public TunaException(String description) {
        super(description);
    }
}
