package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


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