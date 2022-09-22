package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to find an item in the task list.
 *
 * @author Tan Jun Wei
 */
public class FindCommand extends Command {
    private String searchTerm;

    /**
     * Constructs a new FindCommand with the given search term.
     *
     * @param searchTerm The search term to be used.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Outputs the filtered list of tasks.
     *
     * @param tasks The task list to be operated on.
     * @param ui The user interface to be used.
     * @param storage The storage to be used.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList resultsList = tasks.filter(task ->
                task.getDescription().contains(searchTerm));
        ui.showOutput("Here are the tasks in your list: \n" + resultsList);
    }
}
