package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;

public class ByeCommand extends Command {

    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        storage.writeItems(taskList.getTaskList());
        return ui.showByeMessage();
    };

}