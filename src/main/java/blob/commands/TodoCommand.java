package blob.commands;

import blob.common.Messages;
import blob.tasks.ToDo;

public class TodoCommand extends TaskCommand {
    String taskDescription;
    public TodoCommand(String taskDescription) {
        super("todo");
        this.taskDescription = taskDescription;
    }

    public CommandResult execute() {
        ToDo task = new ToDo(taskDescription);
        this.taskList.addTask(task);
        return new CommandResult(Messages.MESSAGE_TASK_ADDED,
                String.format("\n\t\t%s \n", task),
                String.format(Messages.MESSAGE_TASK_LIST_SIZE, taskList.getNumberOfTasks()));
    }
}
