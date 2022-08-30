package duke.command;

import duke.DukeException;
import duke.task.TaskList;

/**
 * Abstract superclass of a command that can be executed upon user request.
 */
public abstract class Command {
    protected static TaskList taskList;
    protected boolean isExit = false;

    /**
     * Sets the TaskList object that all commands use.
     *
     * @param taskList {@code TaskList} object to use.
     */
    public static void setTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }

    /**
     * Executes the given command.
     *
     * @return Message {@code String} from command execution.
     * @throws DukeException Checked exceptions that may occur during command execution.
     */
    public abstract String execute() throws DukeException;

    /**
     * Returns if the {@code Command} is an application terminating command.
     *
     * @return {@code true} if this command is a terminating command, {@code false} otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    };
}
