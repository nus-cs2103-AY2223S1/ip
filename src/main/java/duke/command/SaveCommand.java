package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class SaveCommand extends Command {

    public SaveCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        storage.saveTaskList(taskList);
    }
}
