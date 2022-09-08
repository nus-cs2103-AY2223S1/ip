package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.display(taskList.list());
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.list();
    }

}