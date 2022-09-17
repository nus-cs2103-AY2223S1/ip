package meowmeow;

/**
 * <p>Class MeowmeowException is a concrete class that extends the Exception class.</p>
 * <p>This class is used to create an exception when the user enters an invalid command.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class MeowmeowException extends Exception {

    private String message;

    /**
     * Constructor for MeowmeowException.
     * @param message the message to be displayed to the user.
     */
    MeowmeowException(String message) {
        this.message = message;
    }

    /**
     * Method that returns the message to be displayed to the user.
     * @return the message to be displayed to the user.
     */
    @Override
    public String toString() {
        return message;
    }

}
