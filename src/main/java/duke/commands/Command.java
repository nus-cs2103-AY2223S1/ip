package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;

public abstract class Command {

    /**
     * Returns a String response from Duke after parsing and executing the command.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return string response from Duke
     */
    public abstract String execute(TaskList taskList, Storage storage);
}