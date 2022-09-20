package uwu.command;

import uwu.exception.UwuException;
import uwu.task.TaskList;
import uwu.uwu.Storage;
import uwu.uwu.Ui;


/**
 * The Command class is an abstract class that represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the user command.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If user command is used wrongly.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws UwuException;

    /**
     * Returns whether the user command exits the program.
     * @return true if the command exits the program;
     *         false if the command does not exit the program.
     */
    public abstract boolean isExit();
}
