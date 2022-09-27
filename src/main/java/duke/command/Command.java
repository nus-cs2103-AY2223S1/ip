package duke.command;

import duke.Duke;

/**
 * Represents a Command to be executed by Duke.
 */
public abstract class Command {
    /**
     * The Action to be done by the Command.
     */
    protected Action action;

    /**
     * Constructs the class.
     * @param action The Action to be done by the Command.
     */
    public Command(Action action) {
        this.action = action;
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    public abstract String execute(Duke duke);

    /**
     * Returns whether this command terminates Duke.
     * @return The boolean indicating whether this command terminates Duke.
     */
    public abstract boolean isTerminating();

    /**
     * Returns the Action to be done by the Command.
     * @return The Action to be done by the Command.
     */
    public Action getAction() {
        return this.action;
    }
}
