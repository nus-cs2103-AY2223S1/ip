package kohaku.commands;
import kohaku.daveexceptions.DaveException;

public abstract class Command {

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    public abstract String execute() throws DaveException;

}
