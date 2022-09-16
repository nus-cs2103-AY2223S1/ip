package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Abstract class that denotes the command from users.
 */
public abstract class UserCommand {
    protected TaskList tasks;

    /**
     * Public constructor of UserCommand class.
     */
    UserCommand() {
    };

    /**
     * Public constructor of UserCommand class.
     * @param tasks TaskList containing current tasks.
     */
    UserCommand(TaskList tasks) { this.tasks = tasks; }

    /**
     * Executes the command.
     * @return The result of execution of the command.
     * @throws DukeException Exception could occur during executing commands.
     */
    abstract public String execute() throws DukeException;
}
