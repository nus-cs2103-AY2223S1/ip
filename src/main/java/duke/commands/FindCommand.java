package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class FindCommand extends  Command {

    protected String search;
    private static final String MESSAGE = "\tHere are the mathces from " +
            "your list";

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList searchMatchList = new TaskList(taskList.findTasks(search));
        ui.displayMessage(searchMatchList.taskListString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}