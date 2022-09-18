package cheese.command;

import java.util.ArrayList;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.task.Task;
import cheese.ui.Response;

/**
 * Represents a user command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    /** Keyword to search for in list. */
    private String keyword;

    /**
     * Constructs an instance of <code>FindCommand</code>.
     *
     * @param keyword Keyword given by user to search for in list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes operations to search for task in list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        ArrayList<Task> searchResult = taskList.searchTasksByKeyword(keyword);
        return Response.getSearchResultMessage(searchResult);
    }
}
