package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (taskList.size() > 0) {
            System.out.println("\t Here are your tasks in your list:");
            taskList.printString();
        } else {
            ui.formatMessage("You do not have any tasks.");
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
