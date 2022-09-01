package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.controller.Ui;

/**
 * Represents a command to find tasks in the list.
 */
public class FindCommand implements ICommand {
    private final String searchStr;

    /**
     * Returns an instance of FindCommand.
     * @param searchStr Search keyword.
     */
    public FindCommand(String searchStr) {
        this.searchStr = searchStr;
    }

    /**
     * Executes the command by finding the tasks in the list.
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     * @param ui Ui object for displaying messages.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return taskList.find(this.searchStr);
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
     * Returns if two FindCommands are equal.
     * @param obj Other command.
     * @return True if two FindCommands are equal. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand;
    }
}
