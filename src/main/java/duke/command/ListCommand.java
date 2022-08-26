package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayTasks(tasks);
    }
}
