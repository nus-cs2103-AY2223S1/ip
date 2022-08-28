package duke.command;

import java.util.List;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to search the list of tasks for a specified keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Integer> matches = tasks.search(this.keyword);
        ui.showFindTasks(tasks, matches);
    }
}
