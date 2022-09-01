package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * ExitCommand implements method for exiting Duke.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class ExitCommand extends Command {

    /**
     * Exits the program
     *
     * @param taskList placeholder to match parameters defined in parent abstract class Command
     * @param ui the ui to display Goodbye message
     * @param storage placeholder to match parameters defined in parent abstract class Command
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.goodbyeMessage();
    }

    /**
     *  Terminates the program in Duke.run().
     *
     * @return True as this is the 'exit' command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
