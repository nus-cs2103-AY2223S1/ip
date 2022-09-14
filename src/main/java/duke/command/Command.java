package duke.command;

import java.time.LocalDateTime;

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
     * Constructor with the Action to be done by the Command.
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
    public abstract boolean isTerminated();

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    public abstract String getFormat();

    /**
     * Returns the Action to be done by the Command.
     * @return The Action to be done by the Command.
     */
    public Action getAction() {
        return this.action;
    }
}
