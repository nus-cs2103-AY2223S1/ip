package ip.command;

import ip.utility.TaskList;
import javafx.application.Platform;

/**
 * DukeCommand to end the program.
 */
public class ByeCommand extends DukeCommand {

    /**
     * Does nothing.
     */
    @Override
    public String execute(TaskList taskList) {
        // do nothing
        Platform.exit();
        return "Bye!";
    }
}
