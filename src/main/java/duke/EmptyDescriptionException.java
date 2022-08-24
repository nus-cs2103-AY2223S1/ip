package duke;

/**
 *  A class which encapsulates the empty description type of exception.
 *  @author  Chen Guanzhou
 *  @version v1
 */
public class EmptyDescriptionException extends DukeException{
    public EmptyDescriptionException() {
        super("Description cannot be empty");
    }
}
