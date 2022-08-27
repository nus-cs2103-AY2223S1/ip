package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that prints out all the tasks in the list.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class ShowListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printList();
    }
}
