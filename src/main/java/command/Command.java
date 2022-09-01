package command;

import henry.TaskList;

/**
 * The command class is the base for all commands.
 * It only has one method, execute, which returns a CommandResult.
 * This method is to be implemented by all subclasses of Command.
 */
public abstract class Command {

    protected TaskList taskList;

    /**
     * Returns a CommandResult object based on the type of Command
     * executed.
     */
    public abstract CommandResult execute();

    /**
     * Sets the task list to be used by the command.
     * The task list is then modified by various commands, such as the {@link TodoCommand}.
     * Some commands do not require a task list, and therefore this method is
     * not called in the execution of the command.
     *
     * @param taskList a TaskList object the represents the list of tasks.
     */
    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }
}
