package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * UndoCommand implements method for undoing the previous command.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class UndoCommand extends Command {

    /**
     * Undoes the previous command.
     *
     * @param taskList the task list
     * @param ui the ui to display message after the command is undone
     * @param storage the storage to handle storing of the new task list
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        ui.undoMessage();
        taskList.printList();
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
