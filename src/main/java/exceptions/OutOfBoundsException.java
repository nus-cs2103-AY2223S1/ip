package exceptions;

/**
 * Exception occurs when the index given is out of bounds
 * of the list of tasks.
 */
public class OutOfBoundsException extends TumuException {
    private static final String OUT_OF_BOUNDS_EXCEPTION =
            "Specified index is out of bounds, please key a value from 1 to ";

    /**
     * Constructor for the OutOfBoundsException class.
     * @param listSize Number of tasks in the list currently.
     */
    public OutOfBoundsException(int listSize) {
        super(OUT_OF_BOUNDS_EXCEPTION + listSize);
    }
}
