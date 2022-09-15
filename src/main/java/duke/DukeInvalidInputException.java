package duke;

/**
 * A duke.DukeInvalidInputException is thrown if the user gives an unknown input/command.
 */
public class DukeInvalidInputException extends DukeException {

    /**
     * Public constructor for a duke.DukeInvalidInputException which will print the standardized message when thrown and
     * caught.
     */
    public DukeInvalidInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means D:");
    }
}
