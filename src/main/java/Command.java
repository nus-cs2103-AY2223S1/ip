/**
 * This class encapsulates a command from the user.
 */
public abstract class Command {
    /**
     * Executes the command and returns the result.
     *
     * @return A String signalling that the command has been executed successfully.
     */
    public abstract String execute();
}
