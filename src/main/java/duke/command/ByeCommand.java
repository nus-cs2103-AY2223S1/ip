package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.GoodBye();
    }
}
