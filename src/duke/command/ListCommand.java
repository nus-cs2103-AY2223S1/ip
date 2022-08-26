package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.showList(taskList);
    }
}
