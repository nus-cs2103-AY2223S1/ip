package duke.exception;

import duke.command.Commands;

/**
 * The InvalidArgumentException which handles the case in which
 * the user input invalid arguments.
 *
 * @author Leong Jia Hao Daniel
 */
public class InvalidArgumentException extends DukeException {

    private Commands command;

    /**
     * Constructs the invalid argument exception based on the command.
     *
     * @param commands The command that has the invalid argument exception.
     */
    public InvalidArgumentException(Commands commands) {
        super(commands.name());
        this.command = commands;
    }

    /**
     * Returns the custom message depending on the command.
     *
     * @return The custom error message for each command.
     */
    @Override
    public String toString() {
        String message = "";
        switch (this.command) {
        case Deadline:
            message += "\nPlease add a /by to declare the time the deadline is meant to be set.";
            break;
        case Event:
            message += "\nPlease add a /at to declare the time the event is at.";
            break;
        case Mark:
            //Fallthrough
        case Unmark:
            //Fallthrough
        case Delete:
            message += "\nPlease input a integer within the range of the tasks or completed.";
            break;
        case Date:
            message += "\nPlease input the date in d/MM/yyyy format e.g. 2/12/2019 1800.";
            break;
        case Mass:
            message += "\nPlease input all, event, deadline or todo!";
            break;
        default:
            break;
        }
        return "OOPS!!! The description of the " + this.command
                + " command is invalid!" + message;
    }
}
