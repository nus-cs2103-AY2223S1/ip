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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTaskList(taskList);
        return ui.displayExitMessage();
    }

}