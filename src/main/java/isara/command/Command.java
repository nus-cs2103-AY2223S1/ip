package isara.command;

import isara.exception.IsaraException;
import isara.processor.TaskList;
import isara.storage.Storage;

import java.io.File;

/**
 * Class to represent commands.
 *
 * @author Melissa Anastasia Harijanto
 */
public abstract class Command {
    /** Exit status */
    private boolean hasExit;

    /**
     * Constructor for the Command class.
     *
     * @param hasExit The boolean value whether the system has exited or not.
     */
    protected Command(boolean hasExit) {
        this.hasExit = hasExit;
    }

    /**
     * Executes the actions related to the command.
     *
     * @param tasks The list of tasks where the command is executed.
     * @param file The file to write into.
     * @param storage The storage where the file is located.
     * @return String that is related to the command line.
     * @throws IsaraException The exception that is thrown if one of the commands catch an error.
     */
    public abstract String execute(TaskList tasks, File file, Storage storage) throws IsaraException;

    /**
     * Get the exit status of the bot.
     *
     * @return The exit status.
     */
    public boolean getExitStatus() {
        return hasExit;
    }


}
