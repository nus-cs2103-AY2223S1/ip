package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.List;

public class FindCommand extends Command {

    private final String stringToFind;

    public FindCommand(String stringToFind) {
        this.stringToFind = stringToFind;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        List<Task> taskList = tasks.find(stringToFind).getTasks();
        ui.showFindTask(taskList);
    }

    @Override
    public boolean bye() {
        return false;
    }
}
