package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The findcommand class that helps the user find tasks.
 *
 * @author Leong Jia Hao Daniel
 */
public class FindCommand extends Command {

    private String keyWord;

    /**
     * The constructor for the FindCommand class.
     *
     * @param keyWord The keyword to find.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ArrayList<Task> temp = new ArrayList<Task>();
        for (Task task: taskList.getTaskList()) {
            if (task.toString().contains(keyWord)) {
                temp.add(task);
            }
        }
        return ui.displayMatchingList(temp);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
