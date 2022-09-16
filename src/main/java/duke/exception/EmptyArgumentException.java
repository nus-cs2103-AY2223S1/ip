package duke.exception;

import duke.command.Commands;

/**
 * The Empty Argument exception when the arguments
 * provided by the user are empty.
 *
 * @author Leong Jia Hao Daniel
 */
public class EmptyArgumentException extends DukeException {

    /**
     * Constructs the EmptyArgumentException.
     *
     * @param argument The command that throws the exception.
     */
    public EmptyArgumentException(Commands argument) {
        super(argument.name());
    }

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a " + super.toString() + " cannot be empty.";
    }


}
