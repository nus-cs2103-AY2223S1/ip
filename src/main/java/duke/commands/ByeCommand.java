package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs the preparatory instructions before Duke is stopped.
 */
public class ByeCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
        ui.exit();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ByeCommand) {
            return true;
        }
        return false;
    }
}
