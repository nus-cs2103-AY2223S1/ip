package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.commands.Command;

public class GreetCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGreeting();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
