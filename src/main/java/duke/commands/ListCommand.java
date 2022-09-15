package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;

public class ListCommand extends Command {

    /**
     * Returns a String response from Duke which is the string version of taskList.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return taskList in a string format
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.toString();
    }
}
