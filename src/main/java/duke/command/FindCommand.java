package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to find a task by searching a keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.displayMatchedTasks(task.FindTasks(this.keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
