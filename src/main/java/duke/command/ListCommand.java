package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        ui.showList(list);
    }
}
