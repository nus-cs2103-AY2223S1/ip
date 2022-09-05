package duke.command;

import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;

public class ListCommand extends Command{

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.listAllItems(taskList.getTasks());
    }
}
