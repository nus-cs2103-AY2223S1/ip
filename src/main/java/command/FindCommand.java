package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Encapsulates a user instruction to find tasks according to a search keyword.
 *
 * @author fannyjian
 */
public class FindCommand extends Command {
    private String txt;

    /**
     * Initialises the command based on a search keyword.
     *
     * @param txt Search keyword.
     */
    public FindCommand(String txt) {
        this.txt = txt;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String found = tasks.find(txt);
        ui.showFound(found);
    }
}
