package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

/**
 * Exits the program
 */
public class ExitCommand extends Command {

    /**
     * Constructor which creates a new exit command
     */
    public ExitCommand() {
        super();
    }

    /**
     * Exits the program by printing the goodbye message
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        super.setExit();
        return ui.printBye();
    }
}
