package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filteredTaskList = taskList.filter(keyword);
        String output = filteredTaskList.list();
        ui.display(output);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        TaskList filteredTaskList = taskList.filter(keyword);
        return filteredTaskList.list();
    }
}
