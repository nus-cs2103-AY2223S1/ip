package dobby.commands;

import java.io.IOException;

import dobby.DobbyList;
import dobby.UserInput;

/**
 * The abstract class for all commands.
 */
public abstract class Command {
    protected boolean isBye;

    /**
     * Sets bye flag.
     *
     * @return true of bye flag is set, false otherwise.
     */
    public boolean isBye() {
        return isBye;
    }

    /**
     * Executes the commands.
     *
     * @param dl list of tasks to execute from
     * @param ui user interface
     * @throws IOException
     */
    public abstract void execute(DobbyList dl, UserInput ui) throws IOException;
}