package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/** Command that helps to find tasks in the list */
public class FindCommand extends Command {

    /** A String that user wants to find */
    private String wanted;

    public FindCommand(String wanted) {
        this.wanted = wanted;
    }

    /**
     * Finds the list of wanted tasks and displays them.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return String.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Integer> ls = taskList.findListOfTaskIDs(wanted);
        return ui.showFoundTask(taskList.getSpecificListOfTasks(ls));
    }
}
