package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;


public class ExitCommand extends Command {

    public ExitCommand(String cmd) {
        super(cmd);
    }
    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        ui.showExitMsg();
    }
}
