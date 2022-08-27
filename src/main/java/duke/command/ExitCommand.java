package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
    }
}
