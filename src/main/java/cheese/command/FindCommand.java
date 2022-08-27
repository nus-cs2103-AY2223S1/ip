package cheese.command;

import java.util.ArrayList;
import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.ui.Ui;

/**
 * Represents a user command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    /** Keyword to search for in list. */
    private String keyword;

    /**
     * Constructs an instance of <code>FindCommand</code>.
     * 
     * @param keyword Keyword given by user to serach for in list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes operations to search for task in list.
     * 
     * @param {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ArrayList<Task> searchResult = taskList.searchTasksByKeyword(keyword);
        ui.showSearchResult(searchResult);
    }
}
