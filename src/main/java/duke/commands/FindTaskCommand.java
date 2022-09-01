package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * FindTaskCommand implements method for finding a task in the task list.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class FindTaskCommand extends Command {

    private final String keywords;

    /**
     * Creates new FindTaskCommand object.
     *
     * @param input the input string from the user
     */
    public FindTaskCommand(String input) {
        this.keywords = input.substring(5);
    }

    /**
     * Prints all the tasks in the task list that matches the keywords searched for by the user.
     *
     * @param tasks the TaskList to be search in
     * @param ui the ui to display messages
     * @param storage placeholder to match parameters defined in parent abstract class Command
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.findMessage();
        tasks.findTask(this.keywords);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
