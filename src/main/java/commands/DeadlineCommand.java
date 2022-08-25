package commands;

import common.Messages;
import tasks.Deadline;
import tasks.ToDo;

public class DeadlineCommand extends TaskCommand {
    String taskDescription;
    String by;
    public DeadlineCommand(String taskDescription, String by) {
        super("deadline");
        this.taskDescription = taskDescription;
        this.by = by;
    }

    public CommandResult execute() {
        ToDo task = new Deadline(taskDescription, by);
        this.taskList.addTask(task);
        return new CommandResult(Messages.MESSAGE_NEW_TASK_ADDED_1,
                String.format("\n\t\t%s \n", task),
                String.format(Messages.MESSAGE_NEW_TASK_ADDED_2, taskList.getNumberOfTasks()));
    }
}
