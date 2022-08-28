/**
 * Exits the program
 */
package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    /**
     * Exits the program by printing the goodbye message
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        super.setExit();
        ui.printBye();
    }
}
