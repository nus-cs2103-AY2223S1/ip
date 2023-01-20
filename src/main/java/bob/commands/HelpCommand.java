package bob.commands;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * HelpCommand class to handle "help" keyword
 */
public class HelpCommand extends Command {

    /**
     * Constructor for HelpCommand
     */
    public HelpCommand() {
        super();
    }


    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) {
        return ui.displayCommands();
    }
}
