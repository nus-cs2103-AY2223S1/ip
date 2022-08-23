package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showGoodbye();
    }
}
