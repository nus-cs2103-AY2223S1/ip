package command;

import henry.Task;

/**
 * Responsible for adding Todo tasks to the task list.
 * Todo tasks are tasks with only a description.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private static final String MESSAGE_SUCCESS = "OK. I ADDED THIS TASK TO MY LIST:\n %1$s.";
    private final Task task;

    public TodoCommand(String description) {
        this.task = new Task(Commands.TODO, description, null);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskList.addTask(task)), taskList);
    }
}
