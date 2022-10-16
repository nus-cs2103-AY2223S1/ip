package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;

/**
 * Represents a Chacha command.
 */
public abstract class Command {

    /**
     * Executes command.
     * 
     * @param taskList Task list to perform command on.
     * @param ui Ui to print output message.
     * @param storage Storage.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks if Chacha can exit.
     * 
     * @return Boolean if Chacha can exit.
     */
    public abstract boolean isExit();
    
}
