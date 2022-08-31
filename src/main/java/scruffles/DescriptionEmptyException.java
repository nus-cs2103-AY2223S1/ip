package scruffles;

/**
 * An exception that's thrown when there is a missing description for tasks
 *
 * @author Shamus Tan
 */
public class DescriptionEmptyException extends Exception {

    public DescriptionEmptyException() {
        super("grrrr >:( there is no task name woof woof!");
    }

    public DescriptionEmptyException(String message) {
        super(message);
    }
}
