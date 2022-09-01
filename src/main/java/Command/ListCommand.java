/**
 * Lists the tasks that are currently in the task list
 */
package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Prints all the current task in the tasklist
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.printList();
    }
}
