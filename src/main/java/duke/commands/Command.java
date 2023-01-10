package duke.commands;

import duke.core.DukeException;

/**
 * Encapsulates a command that a parser can invoke.
 */
public abstract class Command {

    /**
     * The string used to invoke the execution of this command.
     */
    protected final String invoker;

    /**
     * Constructor for a command.
     *
     * @param invoker The string used to invoke the execution of this command.
     */
    public Command(String invoker) {
        this.invoker = invoker;
        assert(!this.invoker.isBlank());
    }

    public String getInvoker() {
        return invoker;
    }

    /**
     * Performs the action of this command and returns an output.
     *
     * @param parameters
     * @return String result of the command.
     * @throws DukeException
     */
    public abstract String execute(String parameters) throws DukeException;

}
