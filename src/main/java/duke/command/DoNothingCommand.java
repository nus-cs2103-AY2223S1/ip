package duke.command;

import duke.Duke;

/**
 * Represents a Command to do nothing in Duke.
 */
public class DoNothingCommand extends Command {
    /**
     * The constructor of the class.
     */
    public DoNothingCommand() {
        super(Action.DONOTHING);
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
    public boolean isTerminated() {
        return false;
    }

    /**
     * Returns boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof DoNothingCommand;
    }
}

