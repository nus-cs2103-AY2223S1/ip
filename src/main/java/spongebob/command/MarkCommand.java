package spongebob.command;

import spongebob.SpongebobException;
import spongebob.Storage;
import spongebob.TaskList;

/**
 * Represents a command to mark task as complete in the list.
 */
public class MarkCommand implements ICommand {
    private Integer[] indexList;

    /**
     * Returns an instance of MarkCommand.
     *
     * @param indexList List of indexes of task to be marked.
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
        int numOfMarkedTask = 0;
        for (int index : this.indexList) {
            try {
                taskList.markDone(index);
                numOfMarkedTask++;
            } catch (SpongebobException e) {
                System.out.println(e.getMessage());
            }
        }
        return numOfMarkedTask > 0
                ? String.format("%d task%s been marked completed.",
                        numOfMarkedTask, numOfMarkedTask > 1 ? "s have" : " has")
                : "Please select a task to be marked within the list.";
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
     * Returns if two MarkCommands are equal.
     * @param obj Other command.
     * @return True if two MarkCommands are equal in the number and order of tasks to be marked completed. Else false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkCommand) {
            MarkCommand otherCmd = (MarkCommand) obj;
            if (this.indexList.length == otherCmd.indexList.length) {
                for (int i = 0; i < this.indexList.length; i++) {
                    if (this.indexList[i] != otherCmd.indexList[i]) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
