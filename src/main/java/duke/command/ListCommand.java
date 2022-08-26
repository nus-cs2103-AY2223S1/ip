package duke.command;

import java.util.ArrayList;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles the listing of tasks of a user.
 */
public class ListCommand extends Command {

    /**
     * Initialises a ListCommand.
     *
     * @param commandArgs An array of Strings containing information
     * pertaining to this specific mark command.
     * @param tasks An <code>ArrayList<Task></code>, containing the
     * current existing tasks in the programme.
     */
    public ListCommand(String[] commandArgs, TaskList tasks) {
        super(commandArgs, tasks);
    }

    /**
     * Lists all the tasks of the user's TaskList.
     *
     * @return Returns true for the main Duke class to know to
     * continue asking for input.
     */
    @Override
    public boolean performAction() {
        Ui.print("Sweetie, here is the list of tasks that you have <3");
        tasks.list();
        return true;
    }
}
