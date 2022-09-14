package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Exits the program
 */
public class HelpCommand extends Command {

    /**
     * Constructor which creates a new exit command
     */
    public HelpCommand() {
        super();
    }

    /**
     * Prints the help message
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        super.setExit();
        return ui.printHelp();
    }
}
