package pluto.command;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

/**
 * Commands entered by the user.
 */
public abstract class Command {

    /**
     * Returns whether the command is "bye" or not.
     * @return Exit command or not.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command by modifying the task list, writing to the local file,
     * and displaying appropriate messages.
     * @param tasks List of tasks that the user has.
     * @param ui Display appropriate messages to the user.
     * @param storage Write data to a local file.
     * @throws PlutoException If task list modification or writing to storage generates error.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException;
}
