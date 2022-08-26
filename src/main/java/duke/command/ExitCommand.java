package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.exit();
        storage.save(tasks.toString());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
