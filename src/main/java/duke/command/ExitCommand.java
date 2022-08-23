package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.UI;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Storage storage, UI ui, TaskList tasks) {
        ui.exit();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
