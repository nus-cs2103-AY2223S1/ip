package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.listMessage(list);
    }
}
