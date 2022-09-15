package duke.command;

import duke.task.TaskList;

/**
 * Represents a command issued by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The {@code TaskList} to execute the command on.
     * @return The {@code String} output.
     */
    public abstract String execute(TaskList tasks);
}
