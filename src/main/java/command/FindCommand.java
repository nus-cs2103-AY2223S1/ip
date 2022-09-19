package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 *  Encapsulates a user instruction to find tasks that match a query.
 *
 *  @author Marcus Low
 *
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructs a new command to find tasks based on a query.
     *
     * @param query Query used to find the tasks in the task list.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String results = tasks.find(query);
        return ui.showFound(results);
    }
}
