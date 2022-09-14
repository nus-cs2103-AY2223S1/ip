package Duke.Commands;

import Duke.Exceptions.DukeException;
import Duke.Tasks.TaskList;

/**
 * UserCommand
 * An abstract class to conduct all commands
 */
public abstract class UserCommand {
    protected TaskList tasks;
    UserCommand(){}
    UserCommand(TaskList tasks) { this.tasks = tasks; }

    abstract public String execute() throws DukeException;
}
