package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class encapsulates an Exit Command
 */
public class ListCommand extends Command {

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        ArrayList<Task> list = taskList.list();
        return ui.printList(list);
    }
}
