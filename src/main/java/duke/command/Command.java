package duke.command;

/**
 * This class encapsulates a command from the user.
 */
public abstract class Command {
    /**
     * Executes the Command and returns the result.
     *
     * @return A String signalling that the Command has been executed.
     */
    public abstract String execute();
}
