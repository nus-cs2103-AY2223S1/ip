package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * FindCommand that finds for a task in the TaskList, matched by search input.
 *
 * @author Elgin
 */
public class FindCommand extends Command {
    /** The search input for the task. */
    private final String searchInput;

    /**
     * Constructor for FindCommand.
     *
     * @param arguments The arguments for the task (i.e. the search input string).
     */
    public FindCommand(String arguments) {
        this.searchInput = arguments;
    }

    /**
     * Executes find command and print to user the results of the search.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String searchResults = tasks.findMatches(this.searchInput);

        return Ui.searchResultsMsg(searchResults);
    }
}
