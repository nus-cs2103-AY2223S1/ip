package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;

/**
 * Represents a command to be performed by the chatbot.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks the ToDoList containing all the tasks.
     * @param ui the Ui that interacts with users.
     * @throws InvalidIndexException if index description for a command is invalid.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws InvalidIndexException;

    /**
     * Returns true if command is an exit command,
     * else return false.
     *
     * @return true if command is an exit command, false otherwise.
     */
    public abstract boolean isExit();

}
