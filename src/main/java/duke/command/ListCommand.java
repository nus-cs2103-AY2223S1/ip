package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.controller.Ui;

/**
 * Represents a command to list all task in the list.
 */
public class ListCommand implements ICommand {
    /**
     * Executes the command by listing all task in the list.
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     * @param ui Ui object for displaying messages.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return taskList.toString();
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
     * Returns if two ListCommands are equal.
     * @param obj Other command.
     * @return True if two ListCommands are equal. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
