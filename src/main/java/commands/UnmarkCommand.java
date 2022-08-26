package commands;

import common.Messages;
import exception.InvalidTaskIndexException;
import tasks.Task;

public class UnmarkCommand extends TaskCommand {
    int index;
    public UnmarkCommand(int index) {
        super("unmark");
        this.index = index;
    }

    public CommandResult execute() {
        try {
            Task task = taskList.getTask(index);
            task.markAsUndone();
            return new CommandResult(Messages.MESSAGE_TASK_UNMARKED,
                    String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException exception) {
            return new CommandResult(Messages.MESSAGE_ERROR_TASK_NOT_FOUND);
        }
    }
}
