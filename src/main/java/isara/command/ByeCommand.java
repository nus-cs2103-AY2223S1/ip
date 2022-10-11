package isara.command;

import java.io.File;

import isara.Ui;
import isara.processor.TaskList;
import isara.storage.Storage;

/**
 * Class to represent the command 'bye'.
 *
 * @author Melissa Anastasia Harijanto
 */
public class ByeCommand extends Command {
    /**
     * Constructor for the Bye command.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Prints the exit lines when the user types 'bye'.
     *
     * @param tasks The list of tasks where the command is executed.
     * @param file The file to write into.
     * @param storage The storage where the file is located.
     * @return Exit statement.
     */
    @Override
    public String execute(TaskList tasks, File file, Storage storage) {
        Ui ui = new Ui();
        return ui.exit();
    }
}
