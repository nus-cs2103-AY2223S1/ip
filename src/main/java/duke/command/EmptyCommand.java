package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.UI;

public class EmptyCommand extends Command {

    @Override
    public void execute(Storage storage, UI ui, TaskList tasks) {
        // does nothing
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof EmptyCommand;
    }
}
