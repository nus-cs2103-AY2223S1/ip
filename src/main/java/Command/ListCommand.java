package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.listAllItems(taskList.getTasks());
    }
}
