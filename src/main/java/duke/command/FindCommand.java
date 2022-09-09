package duke.command;

import java.util.List;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Command that searches for a {@link Task} in the {@link TaskList}
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructor for FindCommand
     * @param query the query to search for
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
