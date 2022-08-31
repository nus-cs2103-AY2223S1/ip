package scruffles;

/**
 * An exception that's thrown when the user tries to delete or mark a task that does not exist
 *
 * @author Shamus Tan
 */
public class OutOfBoundsException extends Exception {

    public OutOfBoundsException(int index) {
        super("grrrr >:( there is no number " + index + " item in the list woof woof!");
    }
}
