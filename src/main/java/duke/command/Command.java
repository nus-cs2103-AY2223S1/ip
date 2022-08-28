package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * A command encapsulates the required actions of a valid command made by the user.
 * This action can then be executed with execute().
 */
public abstract class Command {
    /**
     * All possible command words that user can use.
     */
    public enum Commands {
        mark, unmark, todo, deadline, event, delete, bye, list, find
    }

    protected String command;
    protected boolean isExit = false;

    /**
     * Constructor for Command.
     *
     * @param command input string from user.
     */
    public Command(String command) {
        this.command = command;
    }

    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Executes the actions associated with the Command.
     * IOException will not be thrown, but instead have its message printed.
     *
     * @param taskList TaskList containing current tasks.
     * @param storage Storage initialised with output file path.
     * @param ui Ui to print messages for user.
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui);

    /**
     * Executes the actions associated with the Command, without printing messages for user.
     *
     * @param taskList TaskList containing current tasks.
     * @param storage Storage initialised with output file path.
     * @throws IOException if writing change to output file is unsuccessful.
     */
    public abstract void execute(TaskList taskList, Storage storage) throws IOException;
}
