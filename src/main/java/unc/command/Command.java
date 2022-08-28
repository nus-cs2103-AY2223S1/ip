package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.UncException;

/**
 * Abstract class of executable commands.
 */
abstract public class Command {
    /**
     * Actuates the commands.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     * @throws UncException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws UncException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return True iff command is "Bye".
     */
    public abstract boolean isExit();
}
