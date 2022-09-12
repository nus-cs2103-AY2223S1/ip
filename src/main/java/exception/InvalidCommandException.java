package exception;

/**
 *  A class which encapsulates the invalid command type of exception.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {
        super("T_T what are you trying to do? I do not understand");
    }
}
