package duke.command;

import duke.task.TaskList;
import duke.Ui;

/**
 * Class which inherits the Command class for a ListCommand
 *
 * @author kaij77
 * @version 0.1
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand by printing the current list of tasks.
     *
     * @param taskList A list of tasks
     * @param ui       An ui responsible for printing output to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        if (taskList.toString() == null) {
            return ui.print("You have no tasks as of now!");
        } else {
            return ui.print(taskList.toString());
        }
    }
}