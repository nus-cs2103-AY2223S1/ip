package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public class ExitCommand extends Command {
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        ui.showGoodbye();
        storage.writeToFile(tasks);
        System.exit(0);
    }
}
