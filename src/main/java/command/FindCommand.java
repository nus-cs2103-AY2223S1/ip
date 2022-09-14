package command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;


/**
 * Finds a list of tasks based on the task name given by the user
 */
public class FindCommand extends Command {
    private String taskName;

    /**
     * Constructor that creates a new find command with the given taskname
     *
     * @param taskName
     */
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
