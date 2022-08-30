package Commands;
import DaveExceptions.DaveException;

public abstract class Command {

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    public abstract String execute() throws DaveException;

    public boolean getIsRunning() {
        return true;
    }

}
