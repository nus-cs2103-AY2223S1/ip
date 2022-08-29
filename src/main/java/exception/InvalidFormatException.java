package exception;

/**
 * Represent an Invalid format exception
 */
public class InvalidFormatException extends Exception {
    public InvalidFormatException() {
        super("Oops Something went wrong with your formatting");
    }
}