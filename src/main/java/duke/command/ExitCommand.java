package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** Command that allows user to exit. */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Displays an exit message.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return String.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showExitMessage();
    }
}
