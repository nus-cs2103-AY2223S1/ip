package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        storage.saveTaskToFile(task.getListOfTasks());
        ui.displayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
