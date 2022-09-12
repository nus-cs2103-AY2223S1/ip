package sally.command;

import java.util.ArrayList;

import sally.task.Task;
import sally.task.TaskList;
import sally.storage.Storage;
import sally.ui.Ui;

/**
 * FindCommand class to represent command to find task(s) using the given keyword.
 *
 * @author liviamil
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(keyword);
        ui.showFoundTasks(foundTasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}