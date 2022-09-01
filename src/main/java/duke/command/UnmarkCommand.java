package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.controller.Ui;

/**
 * Represents a command to mark task as incomplete in the list.
 */
public class UnmarkCommand implements ICommand {
    private final int index;

    /**
     * Returns an instance of UnmarkCommand.
     * @param index Index of task.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking task as incomplete in the list.
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     * @param ui Ui object for displaying messages.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        try {
            return taskList.unmarkDone(index);
        } catch (DukeException e) {
            return e.getMessage();
        }
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
     * Returns if two UnmarkCommands are equal in index.
     * @param obj Other command.
     * @return True if two UnmarkCommands are equal in index. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnmarkCommand) {
            UnmarkCommand otherCmd = (UnmarkCommand) obj;
            return this.index == otherCmd.index;
        } else {
            return false;
        }
    }
}
