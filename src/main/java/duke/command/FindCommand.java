package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String toFind;

    public FindCommand(String toFind) {
        super();
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.find(toFind);
        ui.showMessage("Here are the tasks found: ");
        ui.showList(foundTasks);
    }
}
