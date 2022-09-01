package Command;

import java.util.ArrayList;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Task;


/**
 * Finds a list of tasks based on the task name given by the user
 */
public class FindCommand extends Command {
    private String taskName;

    public FindCommand(String taskName) {
        super();
        this.taskName = taskName;
    }

    /**
     * Prints all the task that is found with the task name given
     * by the user to the UI
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     * @return string that will be printed in the UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.findTask(taskName);
        return taskList.printList(tasks);
    }
}
