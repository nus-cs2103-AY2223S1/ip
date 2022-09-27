package duke.command;

import duke.Duke;

/**
 * Represents a Command to do nothing in Duke.
 */
public class DoNothingCommand extends Command {
    /**
     * Constructs the class.
     */
    public DoNothingCommand() {
        super(Action.DO_NOTHING);
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        //            do nothing
        return null;
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminating() {
        return false;
    }
}

