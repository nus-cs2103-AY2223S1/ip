package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class ListCommand extends Command {

    /*
     * Display list of current task to user.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.showList(taskList);
    }
}
