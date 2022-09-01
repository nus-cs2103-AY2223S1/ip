package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.controller.Ui;

/**
 * Represents a command to mark task as complete in the list.
 */
public class MarkCommand implements ICommand {
    private final int index;

    /**
     * Returns an instance of MarkCommand.
     * @param index Index of task.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking task as complete in the list.
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     * @param ui Ui object for displaying messages.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            Ui.showMsg(taskList.markDone(index));
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
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
     * Returns if two MarkCommands are equal in index.
     * @param obj Other command.
     * @return True if two MarkCommands are equal in index. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkCommand) {
            MarkCommand otherCmd = (MarkCommand) obj;
            return this.index == otherCmd.index;
        } else {
            return false;
        }
    }
}
