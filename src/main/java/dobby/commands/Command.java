package dobby.commands;

import dobby.*;

import java.io.IOException;

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