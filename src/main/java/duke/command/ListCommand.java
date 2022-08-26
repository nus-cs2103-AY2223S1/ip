package duke.command;

import duke.model.Task;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.list(taskList);
    }
}
