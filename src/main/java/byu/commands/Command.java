package byu.commands;

import byu.exceptions.DuplicateException;
import byu.exceptions.InvalidIndexException;
import byu.util.TaskList;
import byu.util.Ui;

/**
 * A command to be executed by Byu.
 */
public abstract class Command {

    /**
     * Executes the command.
     * Generates and sets Byu's response if there is no exception thrown.
     *
     * @param tasks the TaskList containing all the tasks.
     * @param ui the Ui that interacts with users.
     * @throws InvalidIndexException if executing the command refers to an invalid task index.
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

    /**
     * Generates the response of Byu to the command.
     *
     * @param tasks the TaskList containing all the tasks.
     * @return the response to the command.
     */
    public abstract String generateResponse(TaskList tasks);

}
