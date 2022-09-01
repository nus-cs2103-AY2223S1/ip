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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matches = tasks.find(keyword);
//        ui.findMessage(matches);
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < matches.size(); i++) {
            result += i + 1 + ". " + matches.get(i).toString() + "\n";
        }
        return result;
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
