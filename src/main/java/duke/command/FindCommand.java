package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Encapsulation of the command of searching for a keyword in the list of tasks.
 *
 * @author Sun Ruoxin
 */
public class FindCommand extends Command {
    /** The keyword to be searched. */
    protected String keyword;

    /**
     * Class constructor.
     *
     * @param keyword the keyword to be searched for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command.
     * Searches for the keyword in the list of tasks.
     * Gives feedback to the user.
     *
     * @param tasks the list of tasks
     * @param ui the UI
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> result = tasks.find(keyword);
        ui.findMessage(result);
    }

    /**
     * Returns a boolean value representing whether to exit the programme
     * after the command is executed.
     *
     * @return a boolean value representing whether to exit the programme
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
