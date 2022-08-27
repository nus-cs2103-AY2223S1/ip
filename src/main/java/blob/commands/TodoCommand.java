package blob.commands;

import blob.common.Messages;
import blob.tasks.ToDo;

public class TodoCommand extends TaskCommand {

    /** The description of the todo task to be created */
    private String taskDescription;

    /**
     * Returns a command that when executed will attempt to add a todo to the task list.
     *
     * @param taskDescription The description of the todo task to be created.
     */
    public TodoCommand(String taskDescription) {
        super("todo");
        this.taskDescription = taskDescription;
    }

    /**
     * {@inheritDoc}
     */
    public CommandResult execute() {
        ToDo task = new ToDo(taskDescription);
        this.taskList.addTask(task);
        return new CommandResult(Messages.MESSAGE_TASK_ADDED,
                String.format("\n\t\t%s \n", task),
                String.format(Messages.MESSAGE_TASK_LIST_SIZE, taskList.getNumberOfTasks()));
    }
}
