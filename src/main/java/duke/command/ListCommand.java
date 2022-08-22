package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.UI;

public class ListCommand extends Command {

    @Override
    public void execute(Storage storage, UI ui, TaskList tasks) {
        ui.print(tasks.toString());
    }
}

