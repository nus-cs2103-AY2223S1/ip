package duke.commands;

import duke.data.TaskList;
import duke.tasks.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String[] keyword;

    public FindCommand(String ...keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ArrayList<Task> list = taskList.find(this.keyword);
        ui.printFind(list);
    }
}
