package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command for exiting the chatbot.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Save the current list of tasks into a text file for future reference and
     * display a goodbye message.
     * @param task The TaskList object of the chatbot.
     * @param ui The Ui object of the chatbot.
     * @param storage The storage object of the chatbot.
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        storage.saveTaskToFile(task.getListOfTasks());
        ui.displayBye();
    }

    /**
     * Return true if the command is exit command.
     * @return Return true if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
