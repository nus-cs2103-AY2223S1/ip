package duke.command;

import java.util.Arrays;
import java.util.List;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Searches for a {@link Task} in the {@link TaskList} with a query input from user
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Initializes a FindCommand instance with the query to search for
     * @param query query to search for in the {@link TaskList}
     */
    public FindCommand(String query) {
        assert query != null : "Query cannot be null";
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        List<Task> result = tasks.findTask(this.query);
        return ui.listQueryResult(result);
    }
}
