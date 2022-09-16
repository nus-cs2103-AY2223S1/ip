package ip.command;

import ip.utility.Storage;
import ip.utility.TaskList;
import javafx.application.Platform;

/**
 * DukeCommand to end the program.
 */
public class ByeCommand extends DukeCommand {

    /**
     * Exits the GUI and quits Duke.
     *
     * @param taskList Task list to save before exit.
     * @param storage The storage to save the task list to.
     * @return Farewell message string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        storage.saveToLatest(taskList);
        Platform.exit();
        return "Goodbye!";
    }
}
