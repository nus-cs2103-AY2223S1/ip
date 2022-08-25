package duke.command;

import duke.model.Task;
import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        toggleIsExit();
    }
}
