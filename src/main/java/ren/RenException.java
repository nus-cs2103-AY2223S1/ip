package ren;

/**
 * RenExceptions represent errors in user input when using Ren.
 */
public class RenException extends Exception {
    /**
     * Constructor for a RenException.
     *
     * @param message Error Message.
     */
    public RenException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of this RenException.
     *
     * @return String Representation.
     */
    @Override
    public String toString() {
        return " ☹ Apologies! " + super.getMessage() + "\n";
    }
}
