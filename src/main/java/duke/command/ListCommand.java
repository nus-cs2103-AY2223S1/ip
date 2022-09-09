package duke.command;

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
     *                    pertaining to this specific mark command.
     * @param tasks       An `ArrayList&lt;Task&gt;`, containing the
     *                    current existing tasks in the programme.
     */
    public ListCommand(String[] commandArgs, TaskList tasks) {
        super(commandArgs, tasks);
    }

    /**
     * Lists all the tasks of the user's TaskList.
     *
     * @return Returns the message that MumBot should send to the GUI.
     */
    @Override
    public String performAction() {
        return "Sweetie, here is the list of tasks that you have <3\n"
                + tasks.list();
    }
}
