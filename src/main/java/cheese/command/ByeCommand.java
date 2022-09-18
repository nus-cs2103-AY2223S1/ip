package cheese.command;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.ui.Response;
import javafx.application.Platform;

/**
 * Represents a user command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes operation to display goodbye message.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        Platform.exit();
        return Response.getGoodbyeMessage();
    }
}
