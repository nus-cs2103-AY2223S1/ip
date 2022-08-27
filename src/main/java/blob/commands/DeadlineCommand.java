package blob.commands;

import blob.common.Messages;
import blob.exception.InvalidDateFormatException;
import blob.tasks.Deadline;


public class DeadlineCommand extends TaskCommand {
    String taskDescription;
    String by;
    public DeadlineCommand(String taskDescription, String by) {
        super("deadline");
        this.taskDescription = taskDescription;
        this.by = by;
    }

    public CommandResult execute() {
        try {
            Deadline task = new Deadline(taskDescription, by);
            this.taskList.addTask(task);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED,
                    String.format("\n\t\t%s \n", task),
                    String.format(Messages.MESSAGE_TASK_LIST_SIZE, taskList.getNumberOfTasks()));
        } catch (InvalidDateFormatException exception) {
            return new CommandResult(exception.getBlobMessages());
        }
    }
}
