package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return Ui.getTaskListString(tasks);
    }
}
