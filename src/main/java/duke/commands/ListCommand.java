package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * This class encapsulates a List Command
 * It returns the entire list of tasks or tasks that falls on a specific date
 */
public class ListCommand extends Command {

    private String date;

    /**
     * Constructs a new list command
     *
     * @param info Essential information for the output list
     */
    public ListCommand(String... info) {
        super();
        if (info.length == 0) {
            this.date = null;
        } else {
            this.date = info[0];
        }
    }

    /**
     * Checks if the command is an Exit Command
     *
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
        if (date == null) {
            ArrayList<Task> list = taskList.list();
            return ui.printList(list);
        } else {
            ArrayList<Task> list = taskList.getTasks(date);
            return ui.printTasks(list, date);
        }
    }
}
