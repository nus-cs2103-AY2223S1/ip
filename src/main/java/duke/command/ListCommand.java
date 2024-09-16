package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{



    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        return ui.printArrAsNumberedList(taskList);
    }
}
