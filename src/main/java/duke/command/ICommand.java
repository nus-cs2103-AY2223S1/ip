package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.controller.Ui;

/**
 * An interface for all command types.
 */
public interface ICommand {
    /**
     * Executes the command by adding the task to the list.
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     * @param ui Ui object for displaying messages.
     */
    String execute(Storage storage, TaskList taskList, Ui ui);

    /**
     * Returns if command is an ExitCommand.
     * @return True if command is an ExitCommand. Else false.
     */
    boolean isExit();
}
