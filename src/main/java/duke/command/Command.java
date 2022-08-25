package duke.command;

import duke.component.TaskList;
import duke.exception.DukeException;

/**
 * Represents a user command.
 */
public abstract class Command {

    protected String content;
    protected TaskList tasks;

    /**
     * Constructs a new Command.
     */
    public Command() {}

    /**
     * Constructs a new Command.
     *
     * @param tasks List of all tasks.
     */
    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new Command.
     *
     * @param content Content of the user command.
     * @param tasks List of all tasks.
     */
    public Command(String content, TaskList tasks) {
        this.content = content;
        this.tasks = tasks;
    }

    /**
     * Runs the command given.
     *
     * @return String representation of the message in response to the command.
     * @throws DukeException If the command is unable to run.
     */
    public abstract String run() throws DukeException;

}
