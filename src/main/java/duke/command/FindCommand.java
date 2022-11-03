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
     * Constructs a command to find tasks in the list.
     *
     * @param keyWord The keyword to find.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the find command to find the tasks with similar
     * keywords in the list.
     *
     * @param ui The ui class which handles the user interface.
     * @param storage The storage class which deals with the file.
     * @param taskList The tasklist that stores the tasks.
     * @return The String that represents the list of tasks.
     * @throws DukeException If there is an exception.
     */
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

    /**
     * Returns false if it is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
