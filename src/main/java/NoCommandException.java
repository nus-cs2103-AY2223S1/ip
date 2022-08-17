/**
 * Class to represent NoCommandException.
 */
public class NoCommandException extends Exception {
    /**
     * The Constructor for NoCommandException
     * @param message
     */
    public NoCommandException(String message) {
        super(String.format("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
    }
}
