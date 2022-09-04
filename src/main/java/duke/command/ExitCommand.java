package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command child class that is used to exit the program.
 */
public class ExitCommand extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        return "";
    }
}
