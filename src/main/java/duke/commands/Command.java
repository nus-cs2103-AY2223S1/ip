package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents commands available to be used.
 */
public abstract class Command {
    /**
     * Executes the command based on task list and storage, and calls UI methods when needed.
     *
     * @param taskList The specified tasks.
     * @param ui The Ui used to display messages.
     * @param storage The storage used to load and save tasks locally.
     * @throws DukeException If the command did not complete successfully.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether the chat bot should exit after the command is executed.
     *
     * @return True, if chat bot should exit, otherwise false.
     */
    public abstract boolean isExit();
}
