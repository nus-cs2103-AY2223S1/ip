package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
public class ListCommand extends Command {

    /*
     * Display list of current task to user.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return ui.showList(taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListCommand) {
            return true;
        }
        return false;
    }
}
