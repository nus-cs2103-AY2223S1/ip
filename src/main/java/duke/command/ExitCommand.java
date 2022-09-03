package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.getTerminationString();
    }
}
