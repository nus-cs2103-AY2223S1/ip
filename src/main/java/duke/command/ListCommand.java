package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {
    public ListCommand() {}

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printTaskList(taskList);
    }
}
