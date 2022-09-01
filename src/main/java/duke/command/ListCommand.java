package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * Command to display list to user
 *
 * @author benjytan45678
 * @version 0.1
 */
public class ListCommand extends Command {

    /**
     * show the task list
     *
     * @param tasks specified list of tasks
     * @param ui specific ui object to interact with
     * @param storage specific storage to store the updated task list
     * @throws DukeException specific error message to be thrown
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskArr = tasks.getTaskList();
        String message = ui.showList(taskArr);
        return message;
    }
}
