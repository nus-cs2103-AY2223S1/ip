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
     * The constructor for the InvalidArgumentException.
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
        case DEADLINE:
            message += "\nPlease add a /by to declare the time the deadline is meant to be set.";
            break;
        case EVENT:
            message += "\nPlease add a /at to declare the time the event is at.";
            break;
        case MARK:
        case UNMARK:
        case DELETE:
            message += "\nPlease input a integer within the range of the tasks.";
            break;
        case DATE:
            message += "\nPlease input the date in d/MM/yyyy format e.g. 2/12/2019 1800.";
        }
        return "OOPS!!! The description of the " +  this.command + " is invalid!" + message;
    }
}
