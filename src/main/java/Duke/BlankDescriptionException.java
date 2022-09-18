package Duke;

/**
 * Exception class that throws an exception when
 * blank fields are entered
 */
public class BlankDescriptionException extends DukeException {


    public BlankDescriptionException() {

        super("The description of a command cannot be empty!");
    }

}