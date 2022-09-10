package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        ArrayList<Task> foundTask = taskList.findTask(this.query);
        ui.showFindTaskMessage(foundTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
