package blob.commands;

import blob.common.Messages;
import blob.tasks.Task;

public class MarkCommand extends TaskCommand {
    int index;
    public MarkCommand(int index) {
        super("mark");
        this.index = index;
    }

    public CommandResult execute() {
        try {
            Task task = taskList.getTask(index);
            task.markAsDone();
            return new CommandResult(Messages.MESSAGE_TASK_MARKED,
                    String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException exception) {
            return new CommandResult(Messages.MESSAGE_ERROR_TASK_NOT_FOUND);
        }
    }
}
