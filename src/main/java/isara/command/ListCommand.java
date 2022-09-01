package isara.command;

import java.io.File;

import isara.Ui;
import isara.exception.IsaraException;
import isara.processor.TaskList;
import isara.storage.Storage;

/**
 * Class to represent the command 'list'.
 *
 * @author Melissa Anastasia Harijanto
 */
public class ListCommand extends Command {
    /**
     * Constructor for the List Command.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Prints the list of tasks that the user has inputted.
     *
     * @param tasks The list of tasks where the command is executed.
     * @param file The file to write into.
     * @param storage The storage where the file is located.
     * @return String that is related to the command line.
     * @throws IsaraException An exception is thrown if the bot cannot find any tasks with the keyword.
     */
    @Override
    public String execute(TaskList tasks, File file, Storage storage) {
        Ui ui = new Ui();
        return ui.list(tasks);
    }
}
