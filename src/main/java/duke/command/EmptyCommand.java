package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.controller.Ui;

/**
 * Represents an empty command which has no actions.
 */
public class EmptyCommand implements ICommand {
    /**
     * Executes nothing.
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     * @param ui Ui object for displaying messages.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return "";
    }

    /**
     * Returns if command is an ExitCommand.
     * @return True if command is an ExitCommand. Else false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns if two EmptyCommands are equal.
     * @param obj Other command.
     * @return True if two EmptyCommands are equal. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof EmptyCommand;
    }
}
