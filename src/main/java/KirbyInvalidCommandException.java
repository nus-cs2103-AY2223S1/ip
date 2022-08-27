/**
 * The KirbyInvalidCommandException class inherits from KirbyException
 * and is thrown when there is an undefined input command.
 * @author Sheryl-Lynn Tan (G11)
 */
public class KirbyInvalidCommandException extends KirbyException{
    KirbyInvalidCommandException() {
        super("Aaaaaaah, I don't understand you :(\n" +
                "Try adding a valid command (todo, deadline, event)");
    }
}
