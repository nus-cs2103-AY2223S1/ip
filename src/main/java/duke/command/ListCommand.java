package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;

public class ListCommand extends Command {

    public ListCommand() {}

    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        return ui.showList(taskList);
    };

}
