package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The ListCommand class list the tasks in taskList.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printList(taskList.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
