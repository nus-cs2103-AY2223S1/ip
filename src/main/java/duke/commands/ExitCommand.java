package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Exits the program execution
 */
public class ExitCommand extends Command {


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTaskList(taskList);
        ui.displayExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}