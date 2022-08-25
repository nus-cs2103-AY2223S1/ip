package commands;

import byu.ToDoList;
import byu.Ui;

import exceptions.InvalidIndex;

/**
 * Represents a command to be performed by the chatbot.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param list the ToDoList containing all the tasks.
     * @param ui the Ui that interacts with users.
     * @throws InvalidIndex
     */
    public abstract void execute(ToDoList list, Ui ui) throws InvalidIndex;

    /**
     * Returns true if command is an exit command,
     * else return false.
     *
     * @return true if command is an exit command, false otherwise.
     */
    public abstract boolean isExit();

}
