package isara.command;

import java.io.File;

import isara.Ui;
import isara.processor.TaskList;
import isara.storage.Storage;

/**
 * Class to represent the command 'list'.
 *
 * @author Melissa Anastasia Harijanto
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Prints the list of tasks that the user has inputted.
     *
     * @param tasks The list of tasks where the command is executed.
     */
    @Override
    public String execute(TaskList tasks, File file, Storage storage) {
        Ui ui = new Ui();
        return ui.list(tasks);
    }
}
