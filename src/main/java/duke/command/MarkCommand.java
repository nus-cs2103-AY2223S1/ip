package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to mark task as complete in the list.
 */
public class MarkCommand implements ICommand {
    private Integer[] indexList;

    /**
     * Returns an instance of MarkCommand.
     *
     * @param indexList Index of task.
     */
    public MarkCommand(Integer[] indexList) {
        this.indexList = indexList;
    }

    /**
     * Executes the command by marking task as complete in the list.
     *
     * @param storage Storage object for I/O operations.
     * @param taskList TaskList object for operations on the list of tasks.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder(
                String.format("The following task%s been marked:\n", this.indexList.length > 1 ? "s have" : " has")
        );
        try {
            for (int index : this.indexList) {
                String taskInfo = taskList.markDone(index - 1);
                stringBuilder.append(taskInfo);
            }
        } catch (DukeException e) {
            stringBuilder.append(e.getMessage());
        }
        return stringBuilder.toString();
    }

    /**
     * Returns if command is an ExitCommand.
     *
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
            return true;
        } else {
            return false;
        }
    }
}
