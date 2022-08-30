package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class DeleteAllCommand extends Command {

    /**
     * Clears out the entire task list.
     *
     * @param taskList the task list to be cleared
     * @param ui the ui to display message after the task list has been cleared
     * @param storage the storage to handle storing of the new task list
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.deleteAll();
        ui.deleteAllMessage();
        storage.store(taskList);
    }

    /**
     * Prevents the program from terminating in Duke.run().
     *
     * @return False as this is not the 'exit' command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

