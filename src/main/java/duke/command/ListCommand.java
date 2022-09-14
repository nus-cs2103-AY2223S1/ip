package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public String read(TaskList taskList, Ui ui, Storage storage) {
        int numOfTasks = taskList.getNumOfTasks();
        if (numOfTasks == 0) {
            return ui.getEmptyTaskMessage();
        } else {
            return ui.getList(taskList);
        }
    }
}
