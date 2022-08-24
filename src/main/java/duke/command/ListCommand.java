package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.DukeException;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand () {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        System.out.println(ui.listToString(taskList.getList()));
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
