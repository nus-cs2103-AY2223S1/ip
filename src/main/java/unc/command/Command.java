package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.UncException;

/**
 * Abstract class of executable commands.
 */
public abstract class Command {
    /**
     * Actuates the commands.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     * @return the message to be shown by UI
     * @throws UncException
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws UncException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return True iff command is "Bye".
     */
    public abstract boolean isExit();
}
