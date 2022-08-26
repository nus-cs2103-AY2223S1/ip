package duke.command;

import java.util.List;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * When find commmand is needed
 */
public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        List<Task> result = tasks.findTask(this.query);
        ui.listQueryResult(result);
    }
}
