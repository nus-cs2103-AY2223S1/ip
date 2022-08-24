package duke.commands;

import java.util.ArrayList;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the command to find matching tasks based on a given keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    /**
     * Constructor for a FindCommand.
     * @param keyword The keyword to check each Task's description for.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Checks if the Command is a ByeCommand.
     * @return False.
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * Finds matching tasks whose description contains the given keyword.
     * @param taskList List of tasks.
     * @param ui Shows the list of matching tasks.
     * @param storage Saves the modified list of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.getMatchingTasks(this.keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
