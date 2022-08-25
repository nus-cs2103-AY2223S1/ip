package commands;

import common.Messages;
import tasks.ToDo;

public class TodoCommand extends TaskCommand {
    String taskDescription;
    public TodoCommand(String taskDescription) {
        super("todo");
        this.taskDescription = taskDescription;
    }

    public CommandResult execute() {
        ToDo task = new ToDo(taskDescription);
        this.taskList.addTask(task);
        return new CommandResult(Messages.MESSAGE_NEW_TASK_ADDED_1,
                String.format("\n\t\t%s \n", task),
                String.format(Messages.MESSAGE_NEW_TASK_ADDED_2, taskList.getNumberOfTasks()));
    }
}
