package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.DuplicateException;
import exceptions.InvalidIndexException;

/**
 * A command to be executed by Byu.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks the TaskList containing all the tasks.
     * @param ui the Ui that interacts with users.
     * @throws InvalidIndexException if executing the commands refers to an invalid task index.
     * @throws DuplicateException if executing the command adds a task that already exists.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws InvalidIndexException, DuplicateException;

    /**
     * Returns true if command is an instance of ExitCommand,
     * else return false.
     *
     * @return true if command is an instance of ExitCommand, false otherwise.
     */
    public abstract boolean isExit();

}
