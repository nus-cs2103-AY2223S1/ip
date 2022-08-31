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
    private boolean hasExit;

    protected Command(boolean hasExit) {
        this.hasExit = hasExit;
    }

    /**
     * Executes a command to the list of tasks.
     *
     * @param tasks The list of tasks where the command is executed.
     * @throws IsaraException Exception that will be thrown.
     */
    public abstract String execute(TaskList tasks, File file, Storage storage) throws IsaraException;


}
