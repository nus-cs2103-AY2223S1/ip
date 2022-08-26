package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    /** A String that user wants to find */
    private String wanted;

    public FindCommand(String wanted) {
        this.wanted = wanted;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Integer> ls = taskList.findListOfTaskIDs(wanted);
        ui.showFoundTask(taskList.getSpecificListOfTasks(ls));
    }
}
