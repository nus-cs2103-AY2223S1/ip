package violet.command;

import violet.exception.VioletException;
import violet.TaskList;
import violet.Ui;
import violet.task.Task;

/**
 * ListCommand class to execute the list command.
 */
public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws VioletException {
        int counter = 1;
        this.response = "Here are the tasks in your list:\n";

        for (Task t : taskList.getTaskList()) {
            this.response += counter + "." + t.toString() + "\n";
            counter++;
        }
    }
}
