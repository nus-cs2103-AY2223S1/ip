package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskAndContactList;
import gina.Ui;

/**
 * Represents commands available to be used.
 */
public abstract class Command {
    /**
     * Executes the command based on task and contacts list and storage,
     * and calls UI methods when needed.
     *
     * @param taskAndContactList The specified task and contacts list.
     * @param ui The Ui used to display messages.
     * @param storage The storage used to load and save tasks locally.
     * @throws GinaException If the command did not complete successfully.
     */
    public abstract String execute(TaskAndContactList taskAndContactList, Ui ui, Storage storage) throws GinaException;

    /**
     * Indicates whether the chatbot should exit after the command is executed.
     *
     * @return True, if chatbot should exit, otherwise false.
     */
    public abstract boolean isExit();
}
