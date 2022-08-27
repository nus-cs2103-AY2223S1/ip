package Commands;
import DaveExceptions.DaveException;

interface Command {

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    public String execute() throws DaveException;

}
