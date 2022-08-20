package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String pattern;

    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        TaskList filteredTaskList = taskList.filter(x -> x.getDescription().toLowerCase().contains(this.pattern.toLowerCase()));
        if (filteredTaskList.size() > 0) {
            ui.showOutput("Here are the matching tasks in your list:\n");
            ui.showOutput(filteredTaskList.toString());
        } else {
            ui.showOutput("No matching tasks :(\n");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
