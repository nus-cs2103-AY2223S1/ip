package commands;

import common.Messages;
import exception.InvalidTaskIndexException;
import tasks.Task;
import tasks.TaskList;

public class DeleteCommand extends TaskCommand {
    int index;
    public DeleteCommand(int index) {
        super("delete");
        this.index = index;
    }

    public CommandResult execute() {
        try {
            Task task = taskList.getTask(index);
            taskList.deleteTask(index);
            return new CommandResult(Messages.MESSAGE_TASK_DELETED,
                    String.format("\n\t\t%s \n", task));
        } catch (InvalidTaskIndexException e) {
            return new CommandResult(Messages.MESSAGE_ERROR_TASK_NOT_FOUND);
        }
    }
}
