package manmeowth.exception;

/**
 * Exception representing invalid commands fed to application.
 *
 * @author WR3nd3
 */
public class ManMeowthException extends RuntimeException {


    /**
     * Constructs ManMeowthException object with custom error message.
     *
     * @param message String representing error message to be displayed.
     */
    public ManMeowthException(String message) {
        super(message);
    }



}
