package scruffles;

/**
 * An exception that's thrown when there is a logical error when keying in dates
 *
 * @author Shamus Tan
 */
public class TimeErrorException extends Exception {

    public TimeErrorException(String message) {
        super(message);
    }
}
