package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles the finding of tasks.
 */
public class FindCommand extends Command {

    /**
     * Initialises a FindCommand.
     *
     * @param commandArgs An array of Strings containing information
     *                    pertaining to this specific mark command.
     * @param tasks       An `ArrayList&lt;Task&gt;`, containing the
     *                    current existing tasks in the programme.
     */
    public FindCommand(String[] commandArgs, TaskList tasks) {
        super(commandArgs, tasks);
    }

    /**
     * Searches for tasks that fit the search string.
     *
     * @return Returns the message that MumBot should send to the GUI.
     */
    @Override
    public String performAction() {
        TaskList foundTasks = tasks.searchUsingString(commandArgs[1]);
        return "Sweetie, here is the list of tasks containing your search input <333\n"
            + foundTasks.list();
    }
}

