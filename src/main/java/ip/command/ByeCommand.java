package ip.command;

import ip.utility.Storage;
import ip.utility.TaskList;
import javafx.application.Platform;

/**
 * DukeCommand to end the program.
 */
public class ByeCommand extends DukeCommand {

    /**
     * Quits the GUI instance
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        Platform.exit();
        return "";
    }
}
