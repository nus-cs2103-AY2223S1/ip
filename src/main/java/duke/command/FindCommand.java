package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * FindCommand that inherits Command to search for a keyword
 */
public class FindCommand extends Command {

    private String string;

    /**
     * Constructor of FindCommand
     * @param string the string to be initialised
     */
    public FindCommand(String string) {
        this.string = string;
    }

    /**
     * Returns false to indicate this does not cause program to exit
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the temporary tasklist containing tasks that contains the keyword
     * @param taskList the tasklist to be executed
     * @param ui the ui to be executed
     * @param storage the storage to be executed
     * @return string that contains ui message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList tempList = taskList.findTasks(string);
        return ui.showTaskFound(tempList);
    }
}
