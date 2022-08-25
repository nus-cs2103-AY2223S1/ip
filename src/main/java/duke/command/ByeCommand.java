package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command {

    @Override
    public void run(TaskList taskList) throws DukeException {
        Ui.bye();
        System.exit(0);
    }
}
