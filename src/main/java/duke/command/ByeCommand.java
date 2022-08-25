package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.processor.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks) throws DukeException {
        Ui ui = new Ui();
        ui.exit();
    }
}
