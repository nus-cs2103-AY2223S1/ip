package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ByeCommand extends Command {

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        ui.line();
        System.out.println("Bye. Hope to see you again soon!");
        ui.line();
        storage.saveData(taskList);
    }
}
