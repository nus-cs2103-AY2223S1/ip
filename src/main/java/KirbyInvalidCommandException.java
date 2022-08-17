/**
 * The KirbyInvalidCommandException class inherits from KirbyException
 * and is thrown when there is an undefined input command.
 * @author Sheryl-Lynn Tan (G11)
 */
public class KirbyInvalidCommandException extends KirbyException{
    /**
     * Constructor for KirbyInvalidCommandException.
     */
    KirbyInvalidCommandException() {
        super("T_T Aaaaaaah, I don't understand you :(\n" +
                "Try adding a command (todo, deadline, event) :)");
    }
}
